package com.wondernect.elements.rdb.config;

import com.wondernect.elements.authorize.context.WondernectCommonContext;
import com.wondernect.elements.common.utils.ESJSONObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: AuditorConfig
 * Author: chenxun
 * Date: 2019/4/9 9:31
 * Description:
 */
@Configuration
@EnableJpaAuditing
public class AuditorConfig implements AuditorAware<String> {

    @Autowired
    private WondernectCommonContext wondernectCommonContext;

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.ofNullable(ESJSONObjectUtils.jsonObjectToJsonString(wondernectCommonContext.getAuthorizeData()));
    }
}
