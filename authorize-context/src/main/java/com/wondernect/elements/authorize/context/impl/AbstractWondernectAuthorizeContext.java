package com.wondernect.elements.authorize.context.impl;

import com.wondernect.elements.authorize.context.AuthorizeData;
import com.wondernect.elements.authorize.context.WondernectAuthorizeServerContext;
import com.wondernect.elements.authorize.context.WondernectAuthorizeUserRoleContext;
import com.wondernect.elements.authorize.context.interceptor.AuthorizeAccessType;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.common.utils.ESStringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: AbstractWondernectAuthorizeContext
 * Author: chenxun
 * Date: 2019/3/27 11:39
 * Description: abstract wondernect authorize context
 * WondernectAuthorizeContext的抽象方法实现,用户使用时必须定义一个@Component/@Service类继承该抽象类,在继承类中可以复写指定方法
 */
public abstract class AbstractWondernectAuthorizeContext implements WondernectAuthorizeUserRoleContext, WondernectAuthorizeServerContext {

    @Override
    public AuthorizeData authorizeExpiresToken(String authorizeToken) {
        return null;
    }

    @Override
    public AuthorizeData authorizeUnlimitedToken(String authorizeToken) {
        return null;
    }

    @Override
    public List<String> getCustomValidUserRoles() {
        return new ArrayList<>();
    }

    @Override
    public List<String> getRequestValidUserRoles(String requestUrl, String requestMethod) {
        return new ArrayList<>();
    }

    @Override
    public boolean authorizeUserRole(String userRole, List<String> validUserRoles) {
        if (ESStringUtils.isBlank(userRole)) {
            return true;
        }
        return !validUserRoles.contains(userRole);
    }

    @Override
    public AuthorizeAccessType getAppAccessType(String appId, String userId) {
        return null;
    }

    @Override
    public boolean authorizeAppAccessType(AuthorizeAccessType accessType, AuthorizeAccessType validAppAccessType) {
        if (ESObjectUtils.isNull(accessType)) {
            return false;
        }
        switch (validAppAccessType) {
            case READ:
            {
                return true;
            }
            case WRITE:
            {
                return accessType == AuthorizeAccessType.WRITE;
            }
            default:
            {
                return false;
            }
        }
    }

    @Override
    public boolean authorizeStandAloneAppSecret(String appId, String encryptSecret) {
        return false;
    }
}
