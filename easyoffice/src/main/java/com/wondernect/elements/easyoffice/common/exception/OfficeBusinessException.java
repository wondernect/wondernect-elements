package com.wondernect.elements.easyoffice.common.exception;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.easyoffice.common.error.OfficeBusinessError;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: OfficeBusinessException
 * Author: chenxun
 * Date: 2018/5/23 下午3:21
 * Description: 异常描述
 */
public class OfficeBusinessException extends BusinessException {

    private static final long serialVersionUID = -3147491731328464691L;

    /**
     * 扩展构造方法
     * @param officeBusinessError 错误
     */
    public OfficeBusinessException(OfficeBusinessError officeBusinessError) {
        super(officeBusinessError.getMessage(), officeBusinessError.getCode());
    }
}
