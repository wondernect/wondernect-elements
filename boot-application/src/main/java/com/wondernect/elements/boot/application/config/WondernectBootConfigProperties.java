package com.wondernect.elements.boot.application.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: ServerConfigProperties
 * Author: chenxun
 * Date: 2019/12/1 15:08
 * Description:
 */
@Component
@Primary
@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true)
@ConfigurationProperties(prefix = "server")
public class WondernectBootConfigProperties {

    private int port = 0;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
