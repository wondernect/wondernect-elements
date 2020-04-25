package com.wondernect.elements.thymeleaf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: StringTemplateThymeleaf
 * Author: chenxun
 * Date: 2019/3/2 13:06
 * Description:
 */
@Service(value = "string_template_thymeleaf")
public class StringTemplateThymeleaf extends TemplateThymeleaf {

    @Autowired
    @Qualifier("stringTemplateEngine")
    private TemplateEngine stringTemplateEngine;

    @Override
    protected String generateContent(String template, Context context) {
        return stringTemplateEngine.process(template, context);
    }
}
