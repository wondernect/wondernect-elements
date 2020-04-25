package com.wondernect.elements.rdb.context.impl;

import com.wondernect.elements.rdb.context.AuditorAwareContext;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: AbstractUserAwareContext
 * Author: chenxun
 * Date: 2019/4/9 15:13
 * Description:
 */
public abstract class AbstractAuditorAwareContext implements AuditorAwareContext {

    @Override
    public String getCurrentUser() {
        return null;
    }
}
