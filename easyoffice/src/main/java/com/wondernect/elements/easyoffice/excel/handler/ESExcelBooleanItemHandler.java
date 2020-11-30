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

    private String trueString;

    private String falseString;

    public ESExcelBooleanItemHandler(String itemName, String itemTitle, int itemOrder) {
        super(itemName, itemTitle, itemOrder);
        trueString = "是";
        falseString = "否";
    }

    public ESExcelBooleanItemHandler(String itemName, String itemTitle, int itemOrder, String trueString, String falseString) {
        super(itemName, itemTitle, itemOrder);
        this.trueString = trueString;
        this.falseString = falseString;
    }

    @Override
    public Object handleExcelExportItemObject(Boolean object) {
        if (ESObjectUtils.isNotNull(object)) {
            if (object) {
                return trueString;
            } else {
                return falseString;
            }
        } else {
            return null;
        }
    }

    @Override
    public Boolean handleExcelImportItemObject(Object object) {
        if (ESObjectUtils.isNotNull(object)) {
            if (ESStringUtils.equals(trueString, object.toString())) {
                return true;
            } else if (ESStringUtils.equals(falseString, object.toString())) {
                return false;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
