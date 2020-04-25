package com.wondernect.elements.rdb.config;

import com.wondernect.elements.rdb.context.AuditorAwareContext;
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
    private AuditorAwareContext auditorAwareContext;

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.ofNullable(auditorAwareContext.getCurrentUser());
    }
}
