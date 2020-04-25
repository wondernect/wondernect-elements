package com.wondernect.elements.log.context;

import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.log.em.LogLevel;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.MDC;

import java.util.Map;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: ILogAbstract
 * Author: chenxun
 * Date: 2018/11/29 15:49
 * Description:
 */
public class DefaultLoggerContext implements LoggerContext {

    @Override
    public void constructLogTablePropertyValues(LogLevel logLevel, Map<String, String> extendTablePropertyValues) {
        MDC.put(LoggerContext.TABLE_DEFAULT_PROPERTY_LOG_LEVEL, logLevel.toString());
        if (MapUtils.isNotEmpty(extendTablePropertyValues)) {
            for (String key : extendTablePropertyValues.keySet()) {
                if (ESObjectUtils.isNull(extendTablePropertyValues.get(key))) {
                    MDC.put(key, "UNKNOWN");
                } else {
                    MDC.put(key, extendTablePropertyValues.get(key));
                }
            }
        }
    }
}
