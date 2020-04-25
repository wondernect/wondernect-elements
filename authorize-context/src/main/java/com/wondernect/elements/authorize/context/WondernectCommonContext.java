package com.wondernect.elements.authorize.context;

import com.wondernect.elements.context.ApplicationContext;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: WondernectCommonContext
 * Author: chenxun
 * Date: 2019/3/14 10:41
 * Description: 请求context接口
 */
public interface WondernectCommonContext extends ApplicationContext {

    String getRequestUrl();

    void setRequestUrl(String url);

    String getRequestIp();

    void setRequestIp(String ip);

    String getRequestDevice();

    void setRequestDevice(String device);

    String getDevicePlatform();

    void setDevicePlatform(String devicePlatform);

    String getDeviceIdentifier();

    void setDeviceIdentifier(String deviceIdentifier);

    AuthorizeData getAuthorizeData();

    void setAuthorizeData(AuthorizeData authorizeData);
}
