package com.wondernect.elements.algorithm.snowflake.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: SnowFlakeConfig
 * Author: chenxun
 * Date: 2018/5/29 下午3:06
 * Description: snowflake配置
 */
@Configuration
@EnableConfigurationProperties(SnowFlakeConfigProperties.class)
public class SnowFlakeConfig {
    /**
     * 该config使用@EnableConfigurationProperties(SnowFlakeConfigProperties.class)使SnowFlakeConfigProperties配置生效
     */
}
