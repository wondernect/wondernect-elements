package com.wondernect.elements.thymeleaf.config;

import com.wondernect.elements.property.source.WondernectPropertySourceFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: ThymeleafConfigProperties
 * Author: chenxun
 * Date: 2019/3/2 13:58
 * Description:
 */
@Component
@Primary
@PropertySource(value = {"classpath:application.properties", "classpath:application.yml", "classpath:application.yaml", "classpath:bootstrap.yml", "classpath:bootstrap.yaml"}, ignoreResourceNotFound = true, factory = WondernectPropertySourceFactory.class)
@ConfigurationProperties(prefix = "wondernect.elements.thymeleaf")
public class ThymeleafConfigProperties implements Serializable {

    private static final long serialVersionUID = 7018242746441065005L;

    /**
     * prefix：
     * 当为SpringResourceTemplateResolver标识项目内置模板目录地址(eg：classpath:/templates/)；
     * 当为FileTemplateResolver标识项目服务器磁盘地址(eg：F:/)；
     */
    private String prefix = "";

    private String characterEncoding = "UTF-8";

    private String templateMode = "HTML";

    private Boolean cache = false;

    private long cacheTTL = 0;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getCharacterEncoding() {
        return characterEncoding;
    }

    public void setCharacterEncoding(String characterEncoding) {
        this.characterEncoding = characterEncoding;
    }

    public String getTemplateMode() {
        return templateMode;
    }

    public void setTemplateMode(String templateMode) {
        this.templateMode = templateMode;
    }

    public Boolean getCache() {
        return cache;
    }

    public void setCache(Boolean cache) {
        this.cache = cache;
    }

    public long getCacheTTL() {
        return cacheTTL;
    }

    public void setCacheTTL(long cacheTTL) {
        this.cacheTTL = cacheTTL;
    }
}
