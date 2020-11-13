package com.wondernect.elements.easyoffice.excel;

import cn.afterturn.easypoi.handler.impl.ExcelDataHandlerDefaultImpl;
import cn.afterturn.easypoi.util.PoiPublicUtil;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.common.utils.ESStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: ESExcelImportDataHandler
 * Author: chenxun
 * Date: 2020-11-13 12:47
 * Description: excel数据导入处理
 */
public abstract class ESExcelImportDataHandler extends ExcelDataHandlerDefaultImpl<Map<String, Object>> {

    private static final Logger logger = LoggerFactory.getLogger(ESExcelImportDataHandler.class);

    @Override
    public void setMapValue(Map<String, Object> map, String originKey, Object value) {
        Map<String, String> dataPropertyMapping = getDataPropertyMapping();
        if (ESStringUtils.isNotBlank(dataPropertyMapping.get(originKey))) {
            if (value instanceof Double) {
                map.put(dataPropertyMapping.get(originKey), ESObjectUtils.isNotNull(value) ? PoiPublicUtil.doubleToString((Double) value) : null);
            } else {
                map.put(dataPropertyMapping.get(originKey), ESObjectUtils.isNotNull(value) ? value.toString() : null);
            }
        } else {
            if (value instanceof Double) {
                map.put(originKey, PoiPublicUtil.doubleToString((Double) value));
            } else {
                map.put(originKey, ESObjectUtils.isNotNull(value) ? value.toString() : null);
            }
        }
    }

    public abstract Map<String, String> getDataPropertyMapping();
}
