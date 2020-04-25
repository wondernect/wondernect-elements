package com.wondernect.elements.rdb.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: RDBConfig
 * Author: chenxun
 * Date: 2018/11/21 14:56
 * Description:
 */
@Configuration
@EnableConfigurationProperties(RDBConfigProperties.class)
public class RDBConfig {

    /**
     * 使用@EnableConfigurationProperties(RDBConfigProperties.class)使RDBConfigProperties配置生效
     */
}
