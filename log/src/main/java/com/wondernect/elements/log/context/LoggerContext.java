package com.wondernect.elements.log.context;

import com.wondernect.elements.log.em.LogLevel;

import java.util.Map;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: LoggerContext
 * Author: chenxun
 * Date: 2018/11/29 16:31
 * Description:
 */
public interface LoggerContext {

    /** table默认属性 log_level **/
    String TABLE_DEFAULT_PROPERTY_LOG_LEVEL = "log_level";

    /**
     * 配置日志表属性及值(默认table中有logLevel属性，可在extendKeyValues中添加table自定义属性)
     * @param logLevel 日志级别
     * @param extendTablePropertyValues 扩展key values
     */
    void constructLogTablePropertyValues(LogLevel logLevel, Map<String, String> extendTablePropertyValues);
}
