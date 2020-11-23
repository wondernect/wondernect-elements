package com.wondernect.elements.easyoffice.excel.handler;

import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.common.utils.ESRegexUtils;
import com.wondernect.elements.easyoffice.excel.ESExcelItemHandler;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: ESExcelDoubleItemHandler
 * Author: chenxun
 * Date: 2020-11-22 18:06
 * Description: excel Double item handler
 */
public class ESExcelDoubleItemHandler extends ESExcelItemHandler<Double> {

    public ESExcelDoubleItemHandler(String itemName, String itemTitle, int itemOrder) {
        super(itemName, itemTitle, itemOrder);
    }

    @Override
    public Object handleExcelExportItemObject(Double object) {
        return object;
    }

    @Override
    public Double handleExcelImportItemObject(Object object) {
        if (ESObjectUtils.isNotNull(object)) {
            if (ESRegexUtils.isDouble(object.toString())) {
                return Double.valueOf(object.toString());
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
