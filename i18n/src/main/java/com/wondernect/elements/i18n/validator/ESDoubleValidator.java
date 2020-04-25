package com.wondernect.elements.i18n.validator;

import com.wondernect.elements.common.utils.ESRegexUtils;
import com.wondernect.elements.common.utils.ESStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: ESDoubleValidator
 * Author: chenxun
 * Date: 2019/7/8 11:29
 * Description: double验证器
 */
public class ESDoubleValidator implements ConstraintValidator<ESDouble, String> {

    private static final Logger logger = LoggerFactory.getLogger(ESDoubleValidator.class);

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (ESStringUtils.isBlank(s)) {
            return false;
        }
        return ESRegexUtils.isDouble(s);
    }

    @Override
    public void initialize(ESDouble constraintAnnotation) {
        logger.debug("double验证器初始化");
    }
}
