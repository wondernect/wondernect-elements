package com.wondernect.elements.easyoffice.excel;

import com.wondernect.elements.common.utils.ESObjectUtils;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: ESExcelStringItemHandler
 * Author: chenxun
 * Date: 2020-11-22 18:06
 * Description: excel String item handler
 */
public class ESExcelStringItemHandler extends ESExcelItemHandler<String> {

    public ESExcelStringItemHandler(String itemName, String itemTitle, int itemOrder) {
        super(itemName, itemTitle, itemOrder);
    }

    @Override
    public Object handleExcelExportItemObject(String object) {
        return object;
    }

    @Override
    public String handleExcelImportItemObject(Object object) {
        if (ESObjectUtils.isNotNull(object)) {
            return object.toString();
        } else {
            return null;
        }
    }
}
