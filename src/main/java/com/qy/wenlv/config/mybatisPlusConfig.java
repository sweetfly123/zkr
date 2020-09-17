package com.qy.wenlv.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 功能描述 mybatisplus的分页配置需要
 *
 * @author Barret
 * @date 8/20/2020
 * @return
 */
@Configuration
public class mybatisPlusConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}