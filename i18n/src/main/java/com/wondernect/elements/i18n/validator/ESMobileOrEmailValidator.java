package com.wondernect.elements.i18n.validator;

import com.wondernect.elements.common.utils.ESRegexUtils;
import com.wondernect.elements.common.utils.ESStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: ESMobileOrEmailValidator
 * Author: chenxun
 * Date: 2019/7/8 11:34
 * Description: 手机号码或邮箱验证器
 */
public class ESMobileOrEmailValidator implements ConstraintValidator<ESMobileOrEmail, String> {

    private static final Logger logger = LoggerFactory.getLogger(ESMobileOrEmailValidator.class);

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (ESStringUtils.isBlank(s)) {
            return false;
        }
        if (ESRegexUtils.isMobile(s)) {
            return true;
        }
        if (ESRegexUtils.isEmail(s)) {
            return true;
        }
        return false;
    }

    @Override
    public void initialize(ESMobileOrEmail constraintAnnotation) {
        logger.debug("手机号码或邮箱验证器初始化");
    }
}
