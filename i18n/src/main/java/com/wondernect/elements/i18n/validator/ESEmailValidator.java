package com.wondernect.elements.i18n.validator;

import com.wondernect.elements.common.utils.ESRegexUtils;
import com.wondernect.elements.common.utils.ESStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: ESEmailValidator
 * Author: chenxun
 * Date: 2019/7/8 11:29
 * Description: 邮箱验证器
 */
public class ESEmailValidator implements ConstraintValidator<ESEmail, String> {

    private static final Logger logger = LoggerFactory.getLogger(ESEmailValidator.class);

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (ESStringUtils.isBlank(s)) {
            return false;
        }
        return ESRegexUtils.isEmail(s);
    }

    @Override
    public void initialize(ESEmail constraintAnnotation) {
        logger.debug("邮箱验证器初始化");
    }
}
