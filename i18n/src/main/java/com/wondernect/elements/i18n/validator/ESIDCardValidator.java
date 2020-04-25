package com.wondernect.elements.i18n.validator;

import com.wondernect.elements.common.utils.ESRegexUtils;
import com.wondernect.elements.common.utils.ESStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: ESIDCardValidator
 * Author: chenxun
 * Date: 2019/7/8 11:11
 * Description: 身份证号码验证器
 */
public class ESIDCardValidator implements ConstraintValidator<ESIDCard, String> {

    private static final Logger logger = LoggerFactory.getLogger(ESIDCardValidator.class);

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (ESStringUtils.isBlank(s)) {
            return false;
        }
        return ESRegexUtils.isIdcard(s);
    }

    @Override
    public void initialize(ESIDCard constraintAnnotation) {
        logger.debug("身份证号码验证器初始化");
    }
}
