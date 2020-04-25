package com.wondernect.elements.i18n.validator;

import com.wondernect.elements.common.utils.ESRegexUtils;
import com.wondernect.elements.common.utils.ESStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: ESMobileValidator
 * Author: chenxun
 * Date: 2019/7/8 11:22
 * Description: 手机号码验证器
 */
public class ESMobileValidator implements ConstraintValidator<ESMobile, String> {

    private static final Logger logger = LoggerFactory.getLogger(ESMobileValidator.class);

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (ESStringUtils.isBlank(s)) {
            return false;
        }
        return ESRegexUtils.isMobile(s);
    }

    @Override
    public void initialize(ESMobile constraintAnnotation) {
        logger.debug("手机号码验证器初始化");
    }
}
