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
@Service(value = "file_template_thymeleaf")
public class FileTemplateThymeleaf extends TemplateThymeleaf {

    @Autowired
    @Qualifier("fileTemplateEngine")
    private TemplateEngine fileTemplateEngine;

    @Override
    protected String generateContent(String template, Context context) {
        return fileTemplateEngine.process(template, context);
    }
}
