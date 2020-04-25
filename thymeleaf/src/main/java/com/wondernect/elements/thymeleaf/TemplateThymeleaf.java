package com.wondernect.elements.thymeleaf;

import org.thymeleaf.context.Context;

import java.util.Map;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: TemplateThymeleaf
 * Author: chenxun
 * Date: 2019/3/2 14:34
 * Description:
 */
public abstract class TemplateThymeleaf {

    public final String generateContent(String template, Map<String, Object> varibles) {
        Context context = new Context();
        context.setVariables(varibles);
        return generateContent(template, context);
    }

    protected abstract String generateContent(String template, Context context);
}
