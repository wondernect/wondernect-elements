package com.wondernect.elements.authorize.context.interceptor;

import java.lang.annotation.*;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: AuthorizeUserRole
 * Author: chenxun
 * Date: 2018/6/6 11:05
 * Description: 权限过滤
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthorizeServer {

    AuthorizeAccessType accessType() default AuthorizeAccessType.READ;
}
