package com.wondernect.elements.easyoffice.excel;

import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.common.utils.ESStringUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: ESExcelStringItemHandler
 * Author: chenxun
 * Date: 2020-11-22 18:06
 * Description: excel String item handler
 */
public class ESExcelIDItemHandler<ID extends Serializable> extends ESExcelItemHandler<ID> {

    private Map<ID, String> dictionary = new HashMap<>();

    public ESExcelIDItemHandler(String itemName, String itemTitle, int itemOrder) {
        super(itemName, itemTitle, itemOrder);
    }

    public ESExcelIDItemHandler(String itemName, String itemTitle, int itemOrder, Map<ID, String> dictionary) {
        super(itemName, itemTitle, itemOrder);
        this.dictionary = dictionary;
    }

    @Override
    public Object handleExcelExportItemObject(ID object) {
        if (ESObjectUtils.isNotNull(object)) {
            Object value = null;
            for (ID key : dictionary.keySet()) {
                if (key == object) {
                    value = dictionary.get(key);
                    break;
                }
            }
            return value;
        } else {
            return null;
        }
    }

    @Override
    public ID handleExcelImportItemObject(Object object) {
        if (ESObjectUtils.isNotNull(object)) {
            ID result = null;
            for (ID key : dictionary.keySet()) {
                if (ESStringUtils.equals(dictionary.get(key), object.toString())) {
                    result = key;
                    break;
                }
            }
            return result;
        } else {
            return null;
        }
    }
}
