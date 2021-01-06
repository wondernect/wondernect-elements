package com.wondernect.elements.authorize.context;

import com.wondernect.elements.authorize.context.interceptor.AuthorizeAccessType;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: WondernectAuthorizeContext
 * Author: chenxun
 * Date: 2019/3/27 9:33
 * Description: wondernect authorize context
 */
public interface WondernectAuthorizeServerContext {

    AuthorizeAccessType getAppAccessType(String appId, String userId);

    boolean authorizeAppAccessType(AuthorizeAccessType accessType, AuthorizeAccessType validAppAccessType);

    boolean authorizeStandAloneAppSecret(String appId, String appSecret, String userId);
}
