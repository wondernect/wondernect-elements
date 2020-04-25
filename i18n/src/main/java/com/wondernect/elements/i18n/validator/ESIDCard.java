package com.wondernect.elements.i18n.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: ESIDCard
 * Author: chenxun
 * Date: 2019/7/8 11:08
 * Description: 身份证号码验证器
 */
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ESIDCardValidator.class)
public @interface ESIDCard {

    String message() default "身份证号码为空或格式有误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
