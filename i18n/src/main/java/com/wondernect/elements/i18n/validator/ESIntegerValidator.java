package com.wondernect.elements.i18n.validator;

import com.wondernect.elements.common.utils.ESRegexUtils;
import com.wondernect.elements.common.utils.ESStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: ESIntegerValidator
 * Author: chenxun
 * Date: 2019/7/8 11:29
 * Description: integer验证器
 */
public class ESIntegerValidator implements ConstraintValidator<ESInteger, String> {

    private static final Logger logger = LoggerFactory.getLogger(ESIntegerValidator.class);

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (ESStringUtils.isBlank(s)) {
            return false;
        }
        return ESRegexUtils.isInteger(s);
    }

    @Override
    public void initialize(ESInteger constraintAnnotation) {
        logger.debug("integer验证器初始化");
    }
}
