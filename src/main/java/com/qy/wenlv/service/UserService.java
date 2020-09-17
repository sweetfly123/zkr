package com.qy.wenlv.service;


import com.qy.wenlv.domain.Token;
import com.qy.wenlv.dto.LoginUserDTO;
import com.qy.wenlv.dto.UserDTO;
import com.qy.wenlv.security.MyUser;
import com.qy.wenlv.vo.ResponseVO;
import com.qy.wenlv.vo.UserVO;

import java.util.List;

/**
 * @author Zhifeng.Zeng
 * @description 用户业务接口
 * @date 2019/2/21 14:06
 */
public interface UserService {

    /**
     * @description 添加用户
     */
    void addUser(UserDTO userDTO) throws Exception;

    /**
     * 删除用户
     *
     * @param id
     */
    void deleteUser(Integer id) throws Exception;

    /**
     * @param user
     * @description 修改用户信息
     */
    void updateUser(MyUser user);

    /**
     * @return
     * @description 获取所有用户列表VO
     */
    ResponseVO<List<MyUser>> findAllUserVO();

    /**
     * @return
     * @description 用户登录
     */
    ResponseVO login(LoginUserDTO loginUserDTO);

    Token oauthRefreshToken(String refreshToken);
}
