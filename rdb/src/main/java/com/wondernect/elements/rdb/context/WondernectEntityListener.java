package com.wondernect.elements.rdb.context;

import com.wondernect.elements.common.utils.ESJSONObjectUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.util.Assert;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: WondernectEntityListener
 * Author: chenxun
 * Date: 2020-06-26 21:08
 * Description: 实体类监听器
 */
@Configurable
public class WondernectEntityListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(WondernectEntityListener.class);

    /**
     * 保存前
     */
    @PrePersist
    public void touchForCreate(Object target) {
        Assert.notNull(target, "Entity must not be null!");
        Object object = invokeGetMethod(target, "getCreateUser");
        if (object != null) {
            String createUser;
            String createApp;
            Map<String, String> map = ESJSONObjectUtils.jsonStringToMapClassObject(object.toString(), String.class);
            if (MapUtils.isNotEmpty(map)) {
                createUser = map.get("user_id");
                createApp = map.get("app_id");
                invokeSetMethod(target, createUser, "setCreateUser", String.class);
                invokeSetMethod(target, createUser, "setUpdateUser", String.class);
                invokeSetMethod(target, createApp, "setCreateApp", String.class);
                invokeSetMethod(target, createApp, "setUpdateApp", String.class);
            }
        }
    }

    /**
     * 更新前
     */
    @PreUpdate
    public void touchForUpdate(Object target) {
        Assert.notNull(target, "Entity must not be null!");
        Object object = invokeGetMethod(target, "getUpdateUser");
        if (object != null) {
            String createUser;
            String createApp;
            Map<String, String> map = ESJSONObjectUtils.jsonStringToMapClassObject(object.toString(), String.class);
            if (MapUtils.isNotEmpty(map)) {
                createUser = map.get("user_id");
                createApp = map.get("app_id");
                invokeSetMethod(target, createUser, "setUpdateUser", String.class);
                invokeSetMethod(target, createApp, "setUpdateApp", String.class);
            }
        }
    }

    private Object invokeGetMethod(Object target, String methodName) {
        Method getCreateUserMethod = MethodUtils.getMatchingMethod(target.getClass(), methodName);
        Object value = null;
        try {
            value = getCreateUserMethod.invoke(target);
        } catch (Exception e) {
            LOGGER.error("调用get method接口异常", e);
        }
        return value;
    }

    private void invokeSetMethod(Object target, Object setValue, String methodName, Class... parameterTypes) {
        Method setUpdateAppMethod = MethodUtils.getMatchingMethod(target.getClass(), methodName, parameterTypes);
        try {
            setUpdateAppMethod.invoke(target, setValue);
        } catch (Exception e) {
            LOGGER.error("调用set method接口异常", e);
        }
    }
}
