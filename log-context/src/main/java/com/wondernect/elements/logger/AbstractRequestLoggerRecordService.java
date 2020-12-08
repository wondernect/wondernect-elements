package com.wondernect.elements.logger;

/**
 * Copyright (C), 2017-2020, wondernect.com
 * FileName: RequestLoggerRecordAbstractService
 * Author: chenxun
 * Date: 2020/12/8 18:11
 * Description:
 */
public abstract class AbstractRequestLoggerRecordService implements RequestLoggerRecordService {

    @Override
    public void recordRequestLog(String level, String service, String module, String userId, String operation, String description, String requestId, String url, String method, String argValue, String returnValue, Long runTime, String ip, String devicePlatform, String deviceDescription) {

    }
}
