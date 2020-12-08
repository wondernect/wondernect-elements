package com.wondernect.elements.logger;

import java.lang.annotation.*;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: RequestLogger
 * Author: chenxun
 * Date: 2018/6/6 11:05
 * Description: 请求日志记录
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestLogger {

    String level() default "";

    String service() default "";

    String module() default "";

    String operation() default "";

    String description() default "";
}
