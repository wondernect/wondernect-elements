package com.wondernect.elements.logger.request;

import com.wondernect.elements.common.utils.ESDateTimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Copyright (C), 2017-2020, wondernect.com
 * FileName: RequestLoggerRecordAbstractService
 * Author: chenxun
 * Date: 2020/12/8 18:11
 * Description:
 */
public abstract class AbstractRequestLoggerRecordService implements RequestLoggerRecordService {

    private static final Logger logger = LoggerFactory.getLogger(AbstractRequestLoggerRecordService.class);

    @Override
    public void recordRequestLog(String level, String service, String module, String userId, String appId, String operation, String description, String requestId, String url, String method, String argValue, String returnValue, Long runStartTime, Long runTime, String ip, String devicePlatform, String deviceDescription) {
        logger.info(
                "level:{}, service:{}, module:{}, userId:{}, appId:{}, operation:{}, description:{}, requestId:{}, url:{}, method:{}, argValue:{}, returnValue:{}, runStartTime:{}, runTime:{}ms, ip:{}, devicePlatform:{}, deviceDescription:{}",
                level, service, module, userId, appId, operation, description, requestId, url, method, argValue, returnValue, ESDateTimeUtils.formatDate(runStartTime, "yyyy-MM-dd HH:mm:ss"), runTime, ip, devicePlatform, deviceDescription
        );
    }
}
