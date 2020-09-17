package com.qy.wenlv.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @description 角色返回参数对象
 * @author Zhifeng.Zeng
 * @date 2019/3/7
 */
@Setter
@Getter
@ToString
public class RoleVO {



    /**
     * 角色名(中文)
     */
    private String name;

    /**
     * 角色名
     */
    private String role;
}
