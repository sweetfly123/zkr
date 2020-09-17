package com.qy.wenlv.dto;

import lombok.Data;

/**
 * @author Zhifeng.Zeng
 * @description 添加、修改用户传输参数
 * @date 2019/4/19
 */
@Data
public class UserDTO {

    /**
     * 用户名
     */
    private String account;
    /**
     * 用户姓名
     */
    private String name;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 用户角色id
     */
    private Integer roleId;

}
