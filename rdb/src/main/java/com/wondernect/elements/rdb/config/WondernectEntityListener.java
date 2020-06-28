package com.wondernect.elements.rdb.config;

import com.wondernect.elements.common.utils.ESClassUtils;
import com.wondernect.elements.common.utils.ESJSONObjectUtils;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.util.Assert;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
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
        Object object = ESClassUtils.invokeGetMethod(target, ESClassUtils.getGetMethod(target, "getCreateUser"));
        if (object != null) {
            String createUser;
            String createApp;
            Map<String, String> map = ESJSONObjectUtils.jsonStringToMapClassObject(object.toString(), String.class);
            if (MapUtils.isNotEmpty(map)) {
                createUser = map.get("user_id");
                createApp = map.get("app_id");
                ESClassUtils.invokeSetMethod(target, ESClassUtils.getSetMethod(target, "setCreateUser", String.class), createUser);
                ESClassUtils.invokeSetMethod(target, ESClassUtils.getSetMethod(target, "setUpdateUser", String.class), createUser);
                ESClassUtils.invokeSetMethod(target, ESClassUtils.getSetMethod(target, "setCreateApp", String.class), createApp);
                ESClassUtils.invokeSetMethod(target, ESClassUtils.getSetMethod(target, "setUpdateApp", String.class), createApp);
            }
        }
    }

    /**
     * 更新前
     */
    @PreUpdate
    public void touchForUpdate(Object target) {
        Assert.notNull(target, "Entity must not be null!");
        Object object = ESClassUtils.invokeGetMethod(target, ESClassUtils.getGetMethod(target, "getUpdateUser"));
        if (object != null) {
            String createUser;
            String createApp;
            Map<String, String> map = ESJSONObjectUtils.jsonStringToMapClassObject(object.toString(), String.class);
            if (MapUtils.isNotEmpty(map)) {
                createUser = map.get("user_id");
                createApp = map.get("app_id");
                ESClassUtils.invokeSetMethod(target, ESClassUtils.getSetMethod(target, "setUpdateUser", String.class), createUser);
                ESClassUtils.invokeSetMethod(target, ESClassUtils.getSetMethod(target, "setUpdateApp", String.class), createApp);
            }
        }
    }
}
