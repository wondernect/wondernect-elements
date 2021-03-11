package com.wondernect.elements.rabbitmq.config;

import com.wondernect.elements.property.source.WondernectPropertySourceFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: RabbitMQConfigProperties
 * Author: chenxun
 * Date: 2018/12/11 11:23
 * Description:
 */
@Component
@Primary
@PropertySource(value = {"classpath:application.properties", "classpath:application.yml", "classpath:application.yaml", "classpath:bootstrap.yml", "classpath:bootstrap.yaml"}, ignoreResourceNotFound = true, factory = WondernectPropertySourceFactory.class)
@ConfigurationProperties(prefix = "wondernect.elements.rabbitmq")
public class RabbitMQConfigProperties implements Serializable {

    private static final long serialVersionUID = 7040201969492254374L;

    private String hostname = "localhost";

    private int port = 5672;

    private String username = "guest";

    private String password = "guest";

    private String virtualHost = "/";

    private boolean publisherConfirms = true;

    private Integer concurrentConsumers = 1;

    private Integer maxConcurrentConsumers = 1;

    private String connectionListener;

    private String errorHandler;

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVirtualHost() {
        return virtualHost;
    }

    public void setVirtualHost(String virtualHost) {
        this.virtualHost = virtualHost;
    }

    public boolean isPublisherConfirms() {
        return publisherConfirms;
    }

    public void setPublisherConfirms(boolean publisherConfirms) {
        this.publisherConfirms = publisherConfirms;
    }

    public Integer getConcurrentConsumers() {
        return concurrentConsumers;
    }

    public void setConcurrentConsumers(Integer concurrentConsumers) {
        this.concurrentConsumers = concurrentConsumers;
    }

    public Integer getMaxConcurrentConsumers() {
        return maxConcurrentConsumers;
    }

    public void setMaxConcurrentConsumers(Integer maxConcurrentConsumers) {
        this.maxConcurrentConsumers = maxConcurrentConsumers;
    }

    public String getConnectionListener() {
        return connectionListener;
    }

    public void setConnectionListener(String connectionListener) {
        this.connectionListener = connectionListener;
    }

    public String getErrorHandler() {
        return errorHandler;
    }

    public void setErrorHandler(String errorHandler) {
        this.errorHandler = errorHandler;
    }
}
