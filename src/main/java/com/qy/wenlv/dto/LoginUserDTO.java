package com.qy.wenlv.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @description 登录用户传输参数
 * @author Zhifeng.Zeng
 * @date 2019/4/19 14:26
 */
@Data
@ApiModel(description = "用户信息")
public class LoginUserDTO {

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名",position = 1,required = true)
    private String username;

    /**
     * 用户密码
     */
    @ApiModelProperty(value = "密码",position = 2,required = true)
    private String password;

}
