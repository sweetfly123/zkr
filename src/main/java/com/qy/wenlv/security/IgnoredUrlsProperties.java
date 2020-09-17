package com.qy.wenlv.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 *功能描述
 * @author Barret
 * @date 8/20/2020
 * @return
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "ignored")
public class IgnoredUrlsProperties {
    private List<String> urls = new ArrayList<>();
}
