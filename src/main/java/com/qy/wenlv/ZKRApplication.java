package com.qy.wenlv;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * 功能描述
 *
 * @author Barret
 * @date 8/20/2020
 * @return
 */
@EnableConfigurationProperties
@SpringBootApplication
@MapperScan("com.qy.wenlv.mapper")
public class ZKRApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZKRApplication.class, args);
    }

}
