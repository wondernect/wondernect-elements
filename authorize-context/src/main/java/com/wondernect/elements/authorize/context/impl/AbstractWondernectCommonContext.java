package com.wondernect.elements.authorize.context.impl;

import com.wondernect.elements.authorize.context.AuthorizeData;
import com.wondernect.elements.authorize.context.WondernectCommonContext;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.context.ApplicationContextHolder;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: DefaultWondernectContext
 * Author: chenxun
 * Date: 2019/3/14 11:00
 * Description: default wondernect context
 * WondernectContext的抽象实现,用户使用时必须定义一个@Component/@Service类继承该抽象类,在继承类中可以复写指定方法
 */
public abstract class AbstractWondernectCommonContext implements WondernectCommonContext {

    private static final String URL_KEY = "URL";

    private static final String IP_KEY = "IP";

    private static final String DEVICE_KEY = "DEVICE";

    private static final String DEVICE_PLATFORM_KEY = "DEVICE_PLATFORM";

    private static final String DEVICE_IDENTIFIER_KEY = "DEVICE_IDENTIFIER";

    private static final String AUTHORIZE_DATA_KEY = "AUTHORIZETOKEN_DATA";

    @Override
    public Object get(String s) {
        return ApplicationContextHolder.getContext().get(s);
    }

    @Override
    public void set(String s, Object o) {
        ApplicationContextHolder.getContext().set(s, o);
    }

    @Override
    public String getRequestUrl() {
        if (ESObjectUtils.isNull(get(URL_KEY))) {
            return null;
        }
        return (String) get(URL_KEY);
    }

    @Override
    public void setRequestUrl(String url) {
        set(URL_KEY, url);
    }

    @Override
    public String getRequestIp() {
        if (ESObjectUtils.isNull(get(IP_KEY))) {
            return null;
        }
        return (String) get(IP_KEY);
    }

    @Override
    public void setRequestIp(String ip) {
        set(IP_KEY, ip);
    }

    @Override
    public String getRequestDevice() {
        if (ESObjectUtils.isNull(get(DEVICE_KEY))) {
            return null;
        }
        return (String) get(DEVICE_KEY);
    }

    @Override
    public void setRequestDevice(String device) {
        set(DEVICE_KEY, device);
    }

    @Override
    public String getDevicePlatform() {
        if (ESObjectUtils.isNull(get(DEVICE_PLATFORM_KEY))) {
            return null;
        }
        return (String) get(DEVICE_PLATFORM_KEY);
    }

    @Override
    public void setDevicePlatform(String devicePlatform) {
        set(DEVICE_PLATFORM_KEY, devicePlatform);
    }

    @Override
    public String getDeviceIdentifier() {
        if (ESObjectUtils.isNull(get(DEVICE_IDENTIFIER_KEY))) {
            return null;
        }
        return (String) get(DEVICE_IDENTIFIER_KEY);
    }

    @Override
    public void setDeviceIdentifier(String deviceIdentifier) {
        set(DEVICE_IDENTIFIER_KEY, deviceIdentifier);
    }

    @Override
    public AuthorizeData getAuthorizeData() {
        Object object = get(AUTHORIZE_DATA_KEY);
        if (ESObjectUtils.isNull(object)) {
            AuthorizeData authorizeData = new AuthorizeData();
            set(AUTHORIZE_DATA_KEY, authorizeData);
            return authorizeData;
        }
        return (AuthorizeData) object;
    }
}
