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

    String getRequestIp();

    String getRequestDevice();

    String getDevicePlatform();

    String getDeviceIdentifier();

    AuthorizeData getAuthorizeData();
}
