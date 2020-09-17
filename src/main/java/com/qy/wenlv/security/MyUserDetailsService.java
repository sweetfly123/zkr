package com.qy.wenlv.security;

import com.qy.wenlv.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    public UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("用户的用户名: {}", username);
        // TODO 根据用户名，查找到对应的密码，与权限
        MyUser user = (MyUser) userMapper.loadUserByUsername(username);
        user.setRoles(userMapper.getUserRolesByUserId(user.getId()));
        // 封装用户信息，并返回。参数分别是：用户名，密码，用户权限
        return user;
    }
}