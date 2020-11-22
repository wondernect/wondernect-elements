package com.wondernect.elements.easyoffice.excel;

import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.common.utils.ESStringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: ESExcelStringItemHandler
 * Author: chenxun
 * Date: 2020-11-22 18:06
 * Description: excel String item handler
 */
public class ESExcelEnumItemHandler<E extends Enum> extends ESExcelItemHandler<E> {

    private Map<E, String> dictionary = new HashMap<>();

    public ESExcelEnumItemHandler(String itemName, String itemTitle, int itemOrder) {
        super(itemName, itemTitle, itemOrder);
    }

    public ESExcelEnumItemHandler(String itemName, String itemTitle, int itemOrder, Map<E, String> dictionary) {
        super(itemName, itemTitle, itemOrder);
        this.dictionary = dictionary;
    }

    @Override
    public Object handleExcelExportItemObject(E object) {
        if (ESObjectUtils.isNotNull(object)) {
            Object value = null;
            for (E enumValue : dictionary.keySet()) {
                if (enumValue == object) {
                    value = dictionary.get(enumValue);
                    break;
                }
            }
            return value;
        } else {
            return null;
        }
    }

    @Override
    public E handleExcelImportItemObject(Object object) {
        if (ESObjectUtils.isNotNull(object)) {
            E value = null;
            for (E enumValue : dictionary.keySet()) {
                if (ESStringUtils.equals(dictionary.get(enumValue), object.toString())) {
                    value = enumValue;
                    break;
                }
            }
            return value;
        } else {
            return null;
        }
    }
}
