package com.wondernect.elements.easyoffice.excel.handler;

import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.common.utils.ESRegexUtils;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: ESExcelLongItemHandler
 * Author: chenxun
 * Date: 2020-11-22 18:06
 * Description: excel Long item handler
 */
public class ESExcelLongItemHandler extends ESExcelItemHandler<Long> {

    public ESExcelLongItemHandler(String itemName, String itemTitle, int itemOrder) {
        super(itemName, itemTitle, itemOrder);
    }

    @Override
    public Object handleExcelExportItemObject(Long object) {
        return object;
    }

    @Override
    public Long handleExcelImportItemObject(Object object) {
        if (ESObjectUtils.isNotNull(object)) {
            if (ESRegexUtils.isInteger(object.toString())) {
                return Long.valueOf(object.toString());
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
