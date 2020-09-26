package com.wondernect.elements.i18n.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Copyright (C), 2017-2020, wondernect.com
 * FileName: ESPassword
 * Author: chenxun
 * Date: 2020/6/12 17:40
 * Description: 自定义密码注解
 */
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ESPasswordValidator.class)
public @interface ESPassword {

    boolean emptyValidate() default true;

    int min() default 6;

    int max() default 20;

    boolean strong() default true;

    String passwordRegexPattern() default "^(?![A-Z]*$)(?![a-z]*$)(?![0-9]*$)(?![^a-zA-Z0-9]*$)\\S+$";

    String message() default "密码为空或长度规则有误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
