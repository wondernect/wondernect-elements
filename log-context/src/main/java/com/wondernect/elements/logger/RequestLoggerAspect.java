package com.wondernect.elements.logger;

import com.wondernect.elements.authorize.context.WondernectCommonContext;
import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.utils.ESDateTimeUtils;
import com.wondernect.elements.common.utils.ESJSONObjectUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: AppAspect
 * Author: chenxun
 * Date: 2020-09-25 11:23
 * Description:
 */
@Component
@Aspect
public class RequestLoggerAspect {

    private static final Logger logger = LoggerFactory.getLogger(RequestLoggerAspect.class);

    @Autowired
    private WondernectCommonContext wondernectCommonContext;

    @Autowired
    private RequestLoggerRecordService requestLoggerRecordService;

    @Around(value = "@annotation(requestLogger)")
    public Object enableAppFilter(ProceedingJoinPoint joinPoint, RequestLogger requestLogger) throws Throwable {
        Object response = null;
        long consumeTime = ESDateTimeUtils.getCurrentTimestamp();
        try {
            response = joinPoint.proceed();
            consumeTime = ESDateTimeUtils.getCurrentTimestamp() - consumeTime;
            return response;
        } catch (Exception e) {
            logger.error("请求日志记录执行异常", e);
            throw new BusinessException(e.getLocalizedMessage());
        } finally {
            try {
                requestLoggerRecordService.recordRequestLog(
                        requestLogger.level(),
                        requestLogger.service(),
                        requestLogger.module(),
                        wondernectCommonContext.getAuthorizeData().getUserId(),
                        requestLogger.operation(),
                        requestLogger.description(),
                        wondernectCommonContext.getRequestId(),
                        wondernectCommonContext.getRequestUrl(),
                        wondernectCommonContext.getRequestMethod(),
                        ESJSONObjectUtils.jsonObjectToJsonString(joinPoint.getArgs()),
                        ESJSONObjectUtils.jsonObjectToJsonString(response),
                        consumeTime,
                        wondernectCommonContext.getRequestIp(),
                        wondernectCommonContext.getDevicePlatform(),
                        wondernectCommonContext.getRequestDevice()
                );
            } catch (Exception e) {
                logger.error("请求日志记录写入异常", e);
            }
        }
    }
}
