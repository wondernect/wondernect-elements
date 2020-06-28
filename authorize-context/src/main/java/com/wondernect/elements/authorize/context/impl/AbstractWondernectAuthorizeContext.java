package com.wondernect.elements.authorize.context.impl;

import com.wondernect.elements.authorize.context.WondernectAuthorizeContext;
import com.wondernect.elements.common.utils.ESStringUtils;
import org.apache.commons.collections4.CollectionUtils;

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
public abstract class AbstractWondernectAuthorizeContext implements WondernectAuthorizeContext {

    @Override
    public String authorizeExpiresToken(String authorizeToken) {
        return null;
    }

    @Override
    public String authorizeUnlimitedToken(String authorizeToken) {
        return null;
    }

    @Override
    public String getUserRole(String userId) {
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
        if (CollectionUtils.isEmpty(validUserRoles)) {
            return true;
        }
        if (ESStringUtils.isBlank(userRole)) {
            return false;
        }
        return validUserRoles.contains(userRole);
    }

    @Override
    public boolean authorizeAppSecret(String appId, String encryptSecret) {
        return false;
    }
}
