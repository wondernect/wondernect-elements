package com.wondernect.elements.logger.request;

/**
 * Copyright (C), 2017-2020, wondernect.com
 * FileName: RequestLoggerRecordService
 * Author: chenxun
 * Date: 2020/12/8 18:02
 * Description: 请求日志记录服务
 */
public interface RequestLoggerRecordService {

    String defaultRequestLogLevel();

    String defaultExceptionRequestLogLevel();

    String defaultService();

    void recordRequestLog(
            String level,
            String service,
            String module,
            String userId,
            String appId,
            String operation,
            String description,
            String requestId,
            String url,
            String method,
            String argValue,
            String returnValue,
            Long runStartTime,
            Long runTime,
            String ip,
            String devicePlatform,
            String deviceDescription
    );

    void recordExceptionRequestLog(
            String level,
            String service,
            String module,
            String userId,
            String appId,
            String operation,
            String description,
            String requestId,
            String url,
            String method,
            String argValue,
            String returnValue,
            Long runStartTime,
            Long runTime,
            String ip,
            String devicePlatform,
            String deviceDescription
    );
}
