package com.wondernect.elements.rdb.config;

import com.wondernect.elements.authorize.context.WondernectCommonContext;
import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.utils.ESStringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: AppAspect
 * Author: chenxun
 * Date: 2020-09-25 11:23
 * Description:
 */
@Component
@Aspect
public class AppAspect {

    private static final Logger logger = LoggerFactory.getLogger(AppAspect.class);

    @Autowired
    private WondernectCommonContext wondernectCommonContext;

    @Autowired
    private EntityManager entityManager;

    @Around(value = "@annotation(appFilter)")
    public Object enableAppFilter(ProceedingJoinPoint joinPoint, AppFilter appFilter) throws Throwable {
        try {
            if (ESStringUtils.isNotBlank(wondernectCommonContext.getAuthorizeData().getAppId())) {
                entityManager.unwrap(Session.class).enableFilter("appFilter").setParameter("create_app", wondernectCommonContext.getAuthorizeData().getAppId());
            }
            return joinPoint.proceed();
        } catch (Exception e) {
            logger.error("应用数据过滤执行异常", e);
            throw new BusinessException(e.getLocalizedMessage());
        } finally {
            if (ESStringUtils.isNotBlank(wondernectCommonContext.getAuthorizeData().getAppId())) {
                entityManager.unwrap(Session.class).disableFilter("appFilter");
            }
        }
    }
}
