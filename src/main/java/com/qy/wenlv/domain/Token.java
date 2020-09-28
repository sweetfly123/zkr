package com.qy.wenlv.domain;


import com.qy.wenlv.domain.bean.RefreshTokenBean;
import lombok.Data;

import java.util.List;

/**
 * @author YunFengLiu
 * @description oauth2客户端token参数
 * @date 2019/3/8
 */
@Data
public class Token {

    /**
     * 过期时间
     */
    private String expiration;
    /**
     * 是否过期
     */
    private boolean expired;
    /**
     * 过期时限
     */
    private int expires_in;
    /**
     * refreshToken对象
     */
    private String refresh_token;

    /**
     * token类型
     */
    private String token_type;

    /**
     * access_token值
     */
    private String access_token;

    /**
     * 使用范围
     */
    private String scope;
}
