package com.qy.wenlv.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qy.wenlv.config.ServerConfig;
import com.qy.wenlv.domain.Token;
import com.qy.wenlv.domain.bean.RefreshTokenBean;
import com.qy.wenlv.dto.LoginUserDTO;
import com.qy.wenlv.dto.UserDTO;
import com.qy.wenlv.entity.Role;
import com.qy.wenlv.enums.ResponseEnum;
import com.qy.wenlv.enums.UrlEnum;
import com.qy.wenlv.mapper.RoleMapper;
import com.qy.wenlv.mapper.UserMapper;
import com.qy.wenlv.security.MyUser;
import com.qy.wenlv.service.UserService;
import com.qy.wenlv.utils.BeanUtils;
import com.qy.wenlv.utils.RedisUtil;
import com.qy.wenlv.vo.LoginUserVO;
import com.qy.wenlv.vo.ResponseVO;
import com.qy.wenlv.vo.RoleVO;
import com.qy.wenlv.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.qy.wenlv.config.OAuth2Config.*;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, MyUser> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ServerConfig serverConfig;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addUser(UserDTO userDTO) {
        MyUser user = new MyUser();
        MyUser userByAccount = (MyUser) userMapper.loadUserByUsername(userDTO.getAccount());
        if (userByAccount != null) {
            //此处应该用自定义异常去返回，在这里我就不去具体实现了
            try {
                throw new Exception("This user already exists!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("id", userDTO.getRoleId());
        List<Role> roleList = roleMapper.selectByMap(map);
        //添加用户角色信息
        user.setRoles(roleList);
//        BeanUtils.copyPropertiesIgnoreNull(userDTO, userPO);
        this.save(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteUser(Integer id) {
        MyUser userPO = userMapper.selectById(id);
        if (userPO == null) {
            //此处应该用自定义异常去返回，在这里我就不去具体实现了
            try {
                throw new Exception("This user not exists!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        userMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUser(MyUser user) {
        userMapper.updateById(user);
    }

    @Override
    public ResponseVO<List<MyUser>> findAllUserVO() {
        List<MyUser> userPOList = userMapper.selectByMap(null);
        List<UserVO> userVOList = new ArrayList<>();
        userPOList.forEach(userPO -> {
            UserVO userVO = new UserVO();
            BeanUtils.copyPropertiesIgnoreNull(userPO, userVO);
            RoleVO roleVO = new RoleVO();
            BeanUtils.copyPropertiesIgnoreNull(userPO.getAuthorities(), roleVO);
            userVO.setRole(roleVO);
            userVOList.add(userVO);
        });
        return ResponseVO.success(userPOList);
    }

    @Override
    public ResponseVO login(LoginUserDTO loginUserDTO) {
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
        paramMap.add("client_id", CLIENT_ID);
        paramMap.add("client_secret", CLIENT_SECRET);
        paramMap.add("username", loginUserDTO.getUsername());
        paramMap.add("password", loginUserDTO.getPassword());
        paramMap.add("grant_type", GRANT_TYPE[0]);
        Token token = new Token();
        try {
            //因为oauth2本身自带的登录接口是"/oauth/token"，并且返回的数据类型不能按我们想要的去返回
            //但是我的业务需求是，登录接口是"user/login"，由于我没研究过要怎么去修改oauth2内部的endpoint配置
            //所以这里我用restTemplate(HTTP客户端)进行一次转发到oauth2内部的登录接口，比较简单粗暴
            Object o = restTemplate.postForObject(serverConfig.getUrl() + UrlEnum.LOGIN_URL.getUrl(), paramMap, Object.class);
            JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(o));
            token.setTokenType(jsonObject.getString("token_type"));
            token.setValue(jsonObject.getString("access_token"));
            RefreshTokenBean refreshTokenBean = new RefreshTokenBean();
            refreshTokenBean.setValue(jsonObject.getString("refresh_token"));
            refreshTokenBean.setExpiration(jsonObject.getString("expires_in"));
            token.setRefreshToken(refreshTokenBean);
            token.setExpiresIn((Integer) jsonObject.get("expires_in"));
            token.setScope(Arrays.asList(jsonObject.getString("scope")));

            LoginUserVO loginUserVO = redisUtil.get(token.getValue(), LoginUserVO.class);
            if (loginUserVO != null) {
                //登录的时候，判断该用户是否已经登录过了
                //如果redis里面已经存在该用户已经登录过了的信息
                //我这边要刷新一遍token信息，不然，它会返回上一次还未过时的token信息给你
                //不便于做单点维护
                token = oauthRefreshToken(loginUserVO.getRefreshToken());
                redisUtil.deleteCache(loginUserVO.getAccessToken());
            }
        } catch (RestClientException e) {
            try {
                e.printStackTrace();
                //此处应该用自定义异常去返回，在这里我就不去具体实现了
                //throw new Exception("username or password error");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        //这里我拿到了登录成功后返回的token信息之后，我再进行一层封装，最后返回给前端的其实是LoginUserVO
        LoginUserVO loginUserVO = new LoginUserVO();
        MyUser userPO = (MyUser) userMapper.loadUserByUsername(loginUserDTO.getUsername());
//        BeanUtils.copyPropertiesIgnoreNull(userPO, loginUserVO);
        loginUserVO.setPassword(userPO.getPassword());
        loginUserVO.setAccessToken(token.getValue());
        loginUserVO.setAccessTokenExpiresIn(token.getExpiresIn());
        loginUserVO.setAccessTokenExpiration(token.getExpiration());
        loginUserVO.setExpired(token.isExpired());
        loginUserVO.setScope(token.getScope());
        loginUserVO.setTokenType(token.getTokenType());
        loginUserVO.setRefreshToken(token.getRefreshToken().getValue());
        loginUserVO.setRefreshTokenExpiration(token.getRefreshToken().getExpiration());
        //存储登录的用户
        redisUtil.set(loginUserVO.getAccessToken(), loginUserVO, TimeUnit.HOURS.toSeconds(1));
        return ResponseVO.success(loginUserVO);
    }

    /**
     * @param refreshToken
     * @return
     * @description oauth2客户端刷新token
     * @date 2019/03/05 14:27:22
     * @author Zhifeng.Zeng
     */
    @Override
    public Token oauthRefreshToken(String refreshToken) {
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
        paramMap.add("client_id", CLIENT_ID);
        paramMap.add("client_secret", CLIENT_SECRET);
        paramMap.add("refresh_token", refreshToken);
        paramMap.add("grant_type", GRANT_TYPE[1]);
        Token token = null;
        try {
            token = restTemplate.postForObject(serverConfig.getUrl() + UrlEnum.LOGIN_URL.getUrl(), paramMap, Token.class);
        } catch (RestClientException e) {
            try {
                //此处应该用自定义异常去返回，在这里我就不去具体实现了
                throw new Exception(ResponseEnum.REFRESH_TOKEN_INVALID.getMessage());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return token;
    }

}
