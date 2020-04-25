package com.wondernect.elements.thymeleaf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: FileTemplateThymeleaf
 * Author: chenxun
 * Date: 2019/3/2 13:06
 * Description:
 */
@Service(value = "spring_resource_template_thymeleaf")
public class SpringResourceTemplateThymeleaf extends TemplateThymeleaf {

    @Autowired
    @Qualifier("springResourceTemplateEngine")
    private TemplateEngine springResourceTemplateEngine;

    @Override
    protected String generateContent(String template, Context context) {
        return springResourceTemplateEngine.process(template, context);
    }
}
