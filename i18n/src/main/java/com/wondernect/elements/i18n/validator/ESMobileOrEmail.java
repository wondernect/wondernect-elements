package com.wondernect.elements.i18n.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: ESMobileOrEmail
 * Author: chenxun
 * Date: 2019/7/8 11:33
 * Description: 手机号码或邮箱验证器
 */
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ESMobileOrEmailValidator.class)
public @interface ESMobileOrEmail {

    String message() default "手机号码(邮箱)为空或格式有误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
