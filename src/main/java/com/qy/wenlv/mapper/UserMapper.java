package com.qy.wenlv.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qy.wenlv.entity.Role;
import com.qy.wenlv.security.MyUser;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author:lvyuanjie
 * @Date:2020/5/2 15:24
 */
@Repository
public interface UserMapper extends BaseMapper<MyUser> {
    /**
     * 通过用户名获取用户信息
     *
     * @param username 用户名
     * @return User 用户信息
     */
    Object loadUserByUsername(String username);

    /**
     * 通过用户id获取用户角色集合
     *
     * @param userId 用户id
     * @return List<Role> 角色集合
     */
    List<Role> getUserRolesByUserId(Integer userId);
}

