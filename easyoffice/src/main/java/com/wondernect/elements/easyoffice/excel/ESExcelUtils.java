package com.wondernect.elements.easyoffice.excel;

import com.wondernect.elements.common.utils.ESClassUtils;
import com.wondernect.elements.common.utils.ESJSONObjectUtils;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.common.utils.ESStringUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.collections4.CollectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: ESExcelUtils
 * Author: chenxun
 * Date: 2020-07-21 23:46
 * Description: excel工具类
 */
public final class ESExcelUtils {

    /**
     * 获取实体对象所有EntityExcelItem(默认获取隐藏所有列名)
     */
    public static List<ESExcelItem> getAllEntityExcelItem(Class<?> cls) {
        List<ESExcelItem> excelItemList = new ArrayList<>();
        ApiModel apiModel = cls.getAnnotation(io.swagger.annotations.ApiModel.class);
        if (null != apiModel) {
            String entityName = apiModel.value();
            if (ESStringUtils.isBlank(entityName)) {
                entityName = apiModel.description();
            }
            List<Field> fieldList = ESClassUtils.getFieldsListWithAnnotation(cls, io.swagger.annotations.ApiModelProperty.class);
            if (CollectionUtils.isNotEmpty(fieldList)) {
                for (Field field : fieldList) {
                    String name = ESStringUtils.toUnderLineString(ESClassUtils.getFieldName(field));
                    String type = ESClassUtils.getFieldType(field);
                    String getFieldName = "get" + ESStringUtils.firstLetterToUpper(ESClassUtils.getFieldName(field));
                    String setFieldName = "set" + ESStringUtils.firstLetterToUpper(ESClassUtils.getFieldName(field));
                    ApiModelProperty apiModelProperty = field.getAnnotation(io.swagger.annotations.ApiModelProperty.class);
                    if (null != apiModelProperty) {
                        String title = apiModelProperty.value();
                        if (ESStringUtils.isBlank(title)) {
                            title = apiModelProperty.notes();
                        }
                        ESExcelItem excelItem = new ESExcelItem(
                                cls.getName(),
                                entityName,
                                name,
                                type,
                                title,
                                getFieldName,
                                setFieldName
                        );
                        excelItemList.add(excelItem);
                    }
                }
            }
        }
        return excelItemList;
    }

    /**
     * 导出数据转换
     */
    public static <T> Map<String, Object> getExportData(T object, List<ESExcelItem> excelItemList) {
        Map<String, Object> studyData = new HashMap<>();
        if (CollectionUtils.isNotEmpty(excelItemList)) {
            for (ESExcelItem excelItem : excelItemList) {
                Object itemValue = ESClassUtils.invokeGetMethod(object, ESClassUtils.getGetMethod(object, excelItem.getGetMethodName()));
                if (ESObjectUtils.isNotNull(excelItem.getExportItemHandler())) {
                    itemValue = excelItem.getExportItemHandler().handleExcelExportItemObject(itemValue);
                }
                studyData.put(excelItem.getName(), itemValue);
            }
        }
        return studyData;
    }

    /**
     * 导入数据转换
     */
    public static <T> T getImportObject(Class<T> valueType, Map<String, Object> map, List<ESExcelItem> excelItemList) {
        Map<String, Object> result = new HashMap<>();
        if (CollectionUtils.isNotEmpty(excelItemList)) {
            for (ESExcelItem excelItem : excelItemList) {
                Object itemValue = map.get(excelItem.getName());
                if (null != excelItem.getImportItemHandler() && ESStringUtils.equals(excelItem.getName(), excelItem.getImportItemHandler().getItemName())) {
                    itemValue = excelItem.getImportItemHandler().handleExcelImportItemObject(itemValue);
                    result.put(excelItem.getName(), itemValue);
                }
            }
        }
        return ESJSONObjectUtils.jsonStringToClassObject(ESJSONObjectUtils.jsonObjectToJsonString(result), valueType);
    }
}
