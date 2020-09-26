package com.wondernect.elements.i18n.validator;

import com.wondernect.elements.common.utils.ESRegexUtils;
import com.wondernect.elements.common.utils.ESStringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Copyright (C), 2017-2020, wondernect.com
 * FileName: ESPasswordValidator
 * Author: chenxun
 * Date: 2020/6/12 17:47
 * Description: 密码验证器
 */
public class ESPasswordValidator implements ConstraintValidator<ESPassword, String> {

    private boolean emptyValidate = true;

    private int min = 6;

    private int max = 20;

    private boolean strong = true;

    private String passwordRegexPattern = "^(?![A-Z]*$)(?![a-z]*$)(?![0-9]*$)(?![^a-zA-Z0-9]*$)\\S+$";

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (!emptyValidate && ESStringUtils.isBlank(s)) {
            return true;
        }
        if (ESStringUtils.isBlank(s)) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("密码不能为空").addConstraintViolation();
            return false;
        }
        if (min <= 0 || max <= 0 || max < min) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("密码长度设置最大最小值非法").addConstraintViolation();
            return false;
        }
        if (s.length() < min || s.length() > max) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("密码长度必须在" + min + "与" + max + "之间").addConstraintViolation();
            return false;
        }
        if (strong && !ESRegexUtils.match(passwordRegexPattern, s)) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("密码强度不符合规则要求,请检查修改后重试").addConstraintViolation();
            return false;
        }
        return true;
    }

    @Override
    public void initialize(ESPassword constraintAnnotation) {
        emptyValidate = constraintAnnotation.emptyValidate();
        min = constraintAnnotation.min();
        max = constraintAnnotation.max();
        strong = constraintAnnotation.strong();
        passwordRegexPattern = constraintAnnotation.passwordRegexPattern();
    }
}
