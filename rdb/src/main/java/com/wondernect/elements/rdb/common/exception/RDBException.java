package com.wondernect.elements.rdb.common.exception;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.rdb.common.error.RDBErrorEnum;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: RDBException
 * Author: chenxun
 * Date: 2019/3/14 17:59
 * Description: rdb异常
 */
public class RDBException extends BusinessException {

    private static final long serialVersionUID = -8147779324975601994L;

    /**
     * 基础构造方法
     */
    public RDBException(RDBErrorEnum rdbErrorEnum) {
        super(rdbErrorEnum.getMessage(), rdbErrorEnum.getCode());
    }
}
