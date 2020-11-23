package com.wondernect.elements.easyoffice.excel.handler;

import com.wondernect.elements.common.utils.ESDateTimeUtils;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.common.utils.ESRegexUtils;
import com.wondernect.elements.easyoffice.excel.ESExcelItemHandler;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: ESExcelTimestampItemHandler
 * Author: chenxun
 * Date: 2020-11-22 18:06
 * Description: excel Timestamp item handler
 */
public class ESExcelTimestampItemHandler extends ESExcelItemHandler<Long> {

    private String timestampFormatPattern = "yyyy-MM-dd";

    public ESExcelTimestampItemHandler(String itemName, String itemTitle, int itemOrder) {
        super(itemName, itemTitle, itemOrder);
    }

    public ESExcelTimestampItemHandler(String itemName, String itemTitle, int itemOrder, String timestampFormatPattern) {
        super(itemName, itemTitle, itemOrder);
        this.timestampFormatPattern = timestampFormatPattern;
    }

    @Override
    public Object handleExcelExportItemObject(Long object) {
        if (ESObjectUtils.isNotNull(object)) {
            return ESDateTimeUtils.formatDate(object, this.timestampFormatPattern);
        } else {
            return null;
        }
    }

    @Override
    public Long handleExcelImportItemObject(Object object) {
        if (ESObjectUtils.isNotNull(object)) {
            if (ESRegexUtils.match(this.timestampFormatPattern, object.toString())) {
                return ESDateTimeUtils.formatDate(object.toString(), this.timestampFormatPattern);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
