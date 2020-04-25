package com.wondernect.elements.thymeleaf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: UrlTemplateThymeleaf
 * Author: chenxun
 * Date: 2019/3/2 13:06
 * Description:
 */
@Service(value = "url_template_thymeleaf")
public class UrlTemplateThymeleaf extends TemplateThymeleaf {

    @Autowired
    @Qualifier("urlTemplateEngine")
    private TemplateEngine urlTemplateEngine;

    @Override
    protected String generateContent(String template, Context context) {
        return urlTemplateEngine.process(template, context);
    }
}
