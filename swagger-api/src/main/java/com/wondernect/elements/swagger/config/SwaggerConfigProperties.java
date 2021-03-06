package com.wondernect.elements.swagger.config;

import com.wondernect.elements.property.source.WondernectPropertySourceFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: SwaggerConfigProperties
 * Author: chenxun
 * Date: 2019/4/29 9:15
 * Description:
 */
@Component
@Primary
@PropertySource(value = {"classpath:application.properties", "classpath:application.yml", "classpath:application.yaml", "classpath:bootstrap.yml", "classpath:bootstrap.yaml"}, ignoreResourceNotFound = true, encoding = "UTF-8", factory = WondernectPropertySourceFactory.class)
@ConfigurationProperties(prefix = "wondernect.elements.swagger")
public class SwaggerConfigProperties implements Serializable {

    private static final long serialVersionUID = -3040417596328020484L;

    private boolean enable = true;

    private String pathRegex;

    private String title = "swagger api doc";

    private String serviceUrl = "http://localhost/swagger-ui.html";

    private String contactName = "sunbeam";

    private String contactUrl = "http://www.wondernect.com";

    private String contactEmail = "sunbeamhome@163.com";

    private String description = "http://www.wondernect.com";

    private String version = "v1.0";

    private String appIdPropertyName = "APPID"; // 应用标识

    private String appIdPassAs = "header"; // 应用标识传递位置

    private String appSecretPropertyName = "APPSECRET"; // 传递加密内容的头部key

    private String appSecretPassAs = "header"; // 传递加密内容位置

    private String userIdPropertyName = "USERID"; // 应用标识

    private String userIdPassAs = "header"; // 应用标识传递位置

    private String authorizationPropertyName = "Authorization"; // 传递请求令牌的头部key

    private String authorizationPassAs = "header"; // 传递请求令牌位置

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getPathRegex() {
        return pathRegex;
    }

    public void setPathRegex(String pathRegex) {
        this.pathRegex = pathRegex;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactUrl() {
        return contactUrl;
    }

    public void setContactUrl(String contactUrl) {
        this.contactUrl = contactUrl;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAppIdPropertyName() {
        return appIdPropertyName;
    }

    public void setAppIdPropertyName(String appIdPropertyName) {
        this.appIdPropertyName = appIdPropertyName;
    }

    public String getAppIdPassAs() {
        return appIdPassAs;
    }

    public void setAppIdPassAs(String appIdPassAs) {
        this.appIdPassAs = appIdPassAs;
    }

    public String getAppSecretPropertyName() {
        return appSecretPropertyName;
    }

    public void setAppSecretPropertyName(String appSecretPropertyName) {
        this.appSecretPropertyName = appSecretPropertyName;
    }

    public String getAppSecretPassAs() {
        return appSecretPassAs;
    }

    public void setAppSecretPassAs(String appSecretPassAs) {
        this.appSecretPassAs = appSecretPassAs;
    }

    public String getUserIdPropertyName() {
        return userIdPropertyName;
    }

    public void setUserIdPropertyName(String userIdPropertyName) {
        this.userIdPropertyName = userIdPropertyName;
    }

    public String getUserIdPassAs() {
        return userIdPassAs;
    }

    public void setUserIdPassAs(String userIdPassAs) {
        this.userIdPassAs = userIdPassAs;
    }

    public String getAuthorizationPropertyName() {
        return authorizationPropertyName;
    }

    public void setAuthorizationPropertyName(String authorizationPropertyName) {
        this.authorizationPropertyName = authorizationPropertyName;
    }

    public String getAuthorizationPassAs() {
        return authorizationPassAs;
    }

    public void setAuthorizationPassAs(String authorizationPassAs) {
        this.authorizationPassAs = authorizationPassAs;
    }
}
