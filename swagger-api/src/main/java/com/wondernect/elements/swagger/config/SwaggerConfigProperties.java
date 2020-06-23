package com.wondernect.elements.swagger.config;

import com.wondernect.elements.common.utils.ESStringUtils;
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
@PropertySource(value = {"classpath:application.properties", "classpath:application.yml", "classpath:application.yaml"}, ignoreResourceNotFound = true, factory = WondernectPropertySourceFactory.class)
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
        this.title = ESStringUtils.transformISOToUTF_8(title);
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
        this.contactName = ESStringUtils.transformISOToUTF_8(contactName);
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
        this.description = ESStringUtils.transformISOToUTF_8(description);
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
