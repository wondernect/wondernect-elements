package com.wondernect.elements.easyoffice.excel;

import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import com.wondernect.elements.common.utils.ESClassUtils;
import com.wondernect.elements.common.utils.ESStringUtils;
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
     * @param cls 实体对象
     * @return 实体对象所有EntityExcelItem
     */
    public static List<ESExcelItem> getAllEntityExcelItem(Class<?> cls) {
        List<ESExcelItem> excelItemList = new ArrayList<>();
        List<Field> fieldList = ESClassUtils.getFieldsListWithAnnotation(cls, io.swagger.annotations.ApiModelProperty.class);
        if (CollectionUtils.isNotEmpty(fieldList)) {
            for (Field field : fieldList) {
                String name = ESClassUtils.getFieldName(field);
                String type = ESClassUtils.getFieldType(field);
                String getFieldName = "get" + ESStringUtils.firstLetterToUpper(name);
                String setFieldName = "set" + ESStringUtils.firstLetterToUpper(name);
                ApiModelProperty apiModelProperty = field.getAnnotation(io.swagger.annotations.ApiModelProperty.class);
                if (null != apiModelProperty) {
                    String title = apiModelProperty.value();
                    if (ESStringUtils.isBlank(title)) {
                        title = apiModelProperty.notes();
                    }
                    ESExcelItem excelItem = new ESExcelItem(
                            cls.getName(),
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
        return excelItemList;
    }

    /**
     * 获取所有EntityTitleList（默认所有列名都展示，忽略hidden属性）
     * 本方法不建议使用，所有excel item最好自定义
     * @param excelItemList 实体对象所有属性对象
     * @return EntityTitleList
     */
    public static List<ExcelExportEntity> getAllEntityTitle(List<ESExcelItem> excelItemList) {
        List<ExcelExportEntity> excelExportEntityList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(excelItemList)) {
            for (ESExcelItem excelItem : excelItemList) {
                excelExportEntityList.add(new ExcelExportEntity(excelItem.getTitle(), excelItem.getName()));
            }
        }
        return excelExportEntityList;
    }

    /**
     * 构造ExcelExportEntity
     * @param title 列名
     * @param name 列属性名
     * @param orderNum 排序
     * @return ExcelExportEntity
     */
    public static ExcelExportEntity generateExcelExportEntity(String title, String name, int orderNum) {
        ExcelExportEntity excelExportEntity = new ExcelExportEntity(title, name);
        excelExportEntity.setOrderNum(orderNum);
        return excelExportEntity;
    }

    /**
     * 构造 EntityDataList
     * @param objectList 导出对象数据列表
     * @param excelItemList EntityExcelItem
     * @return EntityDataList
     */
    public static <T> List<Map<String, Object>> getEntityDataList(List<T> objectList, List<ESExcelItem> excelItemList) {
        List<Map<String, Object>> dataList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(objectList)) {
            for (Object object : objectList) {
                Map<String, Object> studyData = new HashMap<>();
                if (CollectionUtils.isNotEmpty(excelItemList)) {
                    for (ESExcelItem excelItem : excelItemList) {
                        if (!excelItem.getHidden()) {
                            Object itemValue = ESClassUtils.invokeGetMethod(object, ESClassUtils.getGetMethod(object, excelItem.getGetMethodName()));
                            if (null != excelItem.getItemHandler() && ESStringUtils.equals(excelItem.getName(), excelItem.getItemHandler().itemName())) {
                                itemValue = excelItem.getItemHandler().handleExcelItemObject(itemValue);
                            }
                            studyData.put(excelItem.getName(), itemValue);
                        }
                    }
                }
                dataList.add(studyData);
            }
        }
        return dataList;
    }
}
