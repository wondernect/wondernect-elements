package com.wondernect.elements.authorize.context;

import java.util.List;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: WondernectAuthorizeContext
 * Author: chenxun
 * Date: 2019/3/27 9:33
 * Description: wondernect authorize context
 */
public interface WondernectAuthorizeContext {

    String authorizeExpiresToken(String authorizeToken);

    String authorizeUnlimitedToken(String authorizeToken);

    String getUserRole(String userId);

    List<String> getCustomValidUserRoles();

    List<String> getRequestValidUserRoles(String requestUrl, String requestMethod);

    boolean authorizeUserRole(String userRole, List<String> validUserRoles);

    boolean authorizeAppSecret(String appId, String encryptSecret);
}
