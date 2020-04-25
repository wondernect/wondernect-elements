package com.wondernect.elements.i18n.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: ESMobile
 * Author: chenxun
 * Date: 2019/7/8 11:21
 * Description: 手机号码验证器
 */
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ESMobileValidator.class)
public @interface ESMobile {

    String message() default "手机号码为空或格式有误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
