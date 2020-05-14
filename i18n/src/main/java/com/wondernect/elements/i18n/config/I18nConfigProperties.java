package com.wondernect.elements.i18n.config;

import com.wondernect.elements.property.source.WondernectPropertySourceFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: I18nConfigProperties
 * Author: chenxun
 * Date: 2018/12/3 15:27
 * Description:
 */
@Component
@Primary
@PropertySource(value = {"classpath:application.properties", "classpath:application.yml", "classpath:application.yaml"}, ignoreResourceNotFound = true, factory = WondernectPropertySourceFactory.class)
@ConfigurationProperties(prefix = "wondernect.elements.i18n")
public class I18nConfigProperties implements Serializable {

    private static final long serialVersionUID = -7011511858485523373L;

    private String defaultLocale = "CHINA"; // CHINA("zh", "CN") - 简体中文; TAIWAN("zh", "TW") - 繁体中文; US("en", "US") - 英文;

    private String localeParam = "lang"; // url上传入语言param key

    private boolean showErrors = false; // 是否显示validate所有错误消息(false标识只显示一条)

    private boolean showException = false; // 是否显示异常堆栈信息

    public String getDefaultLocale() {
        return defaultLocale;
    }

    public void setDefaultLocale(String defaultLocale) {
        this.defaultLocale = defaultLocale;
    }

    public String getLocaleParam() {
        return localeParam;
    }

    public void setLocaleParam(String localeParam) {
        this.localeParam = localeParam;
    }

    public boolean isShowErrors() {
        return showErrors;
    }

    public void setShowErrors(boolean showErrors) {
        this.showErrors = showErrors;
    }

    public boolean isShowException() {
        return showException;
    }

    public void setShowException(boolean showException) {
        this.showException = showException;
    }
}
