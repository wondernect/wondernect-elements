package com.wondernect.elements.authorize.context.interceptor;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: AuthorizeType
 * Author: chenxun
 * Date: 2019/3/27 10:21
 * Description: authorize type
 */
public enum AuthorizeRoleType {

    ONLY_AUTHORIZE, // 只验证用户临时访问码或令牌;

    CONFIG, // 首先验证用户临时访问码或令牌；手动配置validUserRoles或者配置文件指定validUserRoles(优先使用接口配置的validUserRoles,没有则去获取统一配置的validUserRoles)，然后获取用户的role或roles，最终判断是否有权限访问；

    CUSTOM, // 首先验证用户临时访问码或令牌；通过自定义接口获取validUserRoles，然后获取用户的role或roles，最终判断是否有权限访问；

    REQUEST, // 首先验证用户临时访问码或令牌；根据request.getRequestURI(), request.getMethod()通过自定义接口获取对应接口访问的validUserRoles，然后获取用户的role或roles，最终判断是否有权限访问；
}

