package com.wondernect.elements.easyoffice.excel;

import cn.afterturn.easypoi.excel.entity.result.ExcelVerifyHandlerResult;
import cn.afterturn.easypoi.handler.inter.IExcelVerifyHandler;
import com.wondernect.elements.common.response.BusinessData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: ESExcelImportVerifyHandler
 * Author: chenxun
 * Date: 2020-11-13 12:53
 * Description: excel导入数据验证handler
 */
public abstract class ESExcelImportVerifyHandler implements IExcelVerifyHandler<Map<String, Object>> {

    private static final Logger logger = LoggerFactory.getLogger(ESExcelImportVerifyHandler.class);

    @Override
    public ExcelVerifyHandlerResult verifyHandler(Map<String, Object> obj) {
        ExcelVerifyHandlerResult result = new ExcelVerifyHandlerResult();
        BusinessData businessData = verifyData(obj);
        if (businessData.success()) {
            result.setSuccess(true);
        } else {
            result.setSuccess(false);
        }
        result.setMsg(businessData.getMessage());
        return result;
    }

    public abstract BusinessData verifyData(Map<String, Object> object);
}
