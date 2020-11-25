package com.wondernect.elements.easyoffice.excel.handler;

import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.common.utils.ESStringUtils;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: ESExcelBooleanItemHandler
 * Author: chenxun
 * Date: 2020-11-22 18:06
 * Description: excel Boolean item handler
 */
public class ESExcelBooleanItemHandler extends ESExcelItemHandler<Boolean> {

    public ESExcelBooleanItemHandler(String itemName, String itemTitle, int itemOrder) {
        super(itemName, itemTitle, itemOrder);
    }

    @Override
    public Object handleExcelExportItemObject(Boolean object) {
        if (ESObjectUtils.isNotNull(object)) {
            if (object) {
                return "是";
            } else {
                return "否";
            }
        } else {
            return null;
        }
    }

    @Override
    public Boolean handleExcelImportItemObject(Object object) {
        if (ESObjectUtils.isNotNull(object)) {
            if (ESStringUtils.equals("是", object.toString())) {
                return true;
            } else if (ESStringUtils.equals("否", object.toString())) {
                return false;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
