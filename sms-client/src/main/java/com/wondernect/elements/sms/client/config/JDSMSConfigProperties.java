package com.wondernect.elements.sms.client.config;

import com.wondernect.elements.property.source.WondernectPropertySourceFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: JDSMSConfigProperties
 * Author: chenxun
 * Date: 2019/9/12 11:45
 * Description: 京东短信服务配置
 */
@Component
@Primary
@PropertySource(value = {"classpath:application.properties", "classpath:application.yml", "classpath:application.yaml"}, ignoreResourceNotFound = true, factory = WondernectPropertySourceFactory.class)
@ConfigurationProperties(prefix = "wondernect.elements.sms-client.jd")
public class JDSMSConfigProperties {

    private String url = "http://39.98.249.54:7862/sms"; // 短信发送请求地址

    private String action = "send"; // 发送任务命令

    private String account = ""; // 发送用户账号

    private String password = ""; // 发送账号密码

    private String extno = ""; // 接入号

    private String rt = "json"; // 响应数据类型

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getExtno() {
        return extno;
    }

    public void setExtno(String extno) {
        this.extno = extno;
    }

    public String getRt() {
        return rt;
    }

    public void setRt(String rt) {
        this.rt = rt;
    }
}
