package com.wondernect.elements.easyoffice.excel.model;

import com.wondernect.elements.easyoffice.excel.handler.ESExcelItemHandler;

import java.util.Objects;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: ESExcelExportItem
 * Author: chenxun
 * Date: 2020-07-21 23:12
 * Description: excel对象
 */
public class ESExcelItem {

    // 实体类
    private String entity;

    // 实体类名称
    private String entityName;

    // 属性名
    private String name;

    // 属性类型
    private String type;

    // 属性导出标题
    private String title;

    // 属性get方法名
    private String getMethodName;

    // 属性set方法名
    private String setMethodName;

    // 排序顺序
    private int orderNum = 0;

    // 导出时该列属性值处理方法
    private ESExcelItemHandler exportItemHandler;

    // 导入时该列属性值处理方法
    private ESExcelItemHandler importItemHandler;

    public ESExcelItem() {
    }

    public ESExcelItem(String entity, String entityName, String name, String type, String title, String getMethodName, String setMethodName) {
        this.entity = entity;
        this.entityName = entityName;
        this.name = name;
        this.type = type;
        this.title = title;
        this.getMethodName = getMethodName;
        this.setMethodName = setMethodName;
        this.orderNum = 0;
        this.exportItemHandler = null;
        this.importItemHandler = null;
    }

    public ESExcelItem(String entity, String entityName, String name, String type, String title, String getMethodName, String setMethodName, int orderNum) {
        this.entity = entity;
        this.entityName = entityName;
        this.name = name;
        this.type = type;
        this.title = title;
        this.getMethodName = getMethodName;
        this.setMethodName = setMethodName;
        this.orderNum = orderNum;
        this.exportItemHandler = null;
        this.importItemHandler = null;
    }

    public ESExcelItem(String entity, String entityName, String name, String type, String title, String getMethodName, String setMethodName, int orderNum, ESExcelItemHandler exportItemHandler) {
        this.entity = entity;
        this.entityName = entityName;
        this.name = name;
        this.type = type;
        this.title = title;
        this.getMethodName = getMethodName;
        this.setMethodName = setMethodName;
        this.orderNum = orderNum;
        this.exportItemHandler = exportItemHandler;
        this.importItemHandler = null;
    }

    public ESExcelItem(String entity, String entityName, String name, String type, String title, String getMethodName, String setMethodName, int orderNum, ESExcelItemHandler exportItemHandler, ESExcelItemHandler importItemHandler) {
        this.entity = entity;
        this.entityName = entityName;
        this.name = name;
        this.type = type;
        this.title = title;
        this.getMethodName = getMethodName;
        this.setMethodName = setMethodName;
        this.orderNum = orderNum;
        this.exportItemHandler = exportItemHandler;
        this.importItemHandler = importItemHandler;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ESExcelItem excelItem = (ESExcelItem) o;
        return Objects.equals(entity, excelItem.entity) &&
                Objects.equals(entityName, excelItem.entityName) &&
                Objects.equals(name, excelItem.name) &&
                Objects.equals(type, excelItem.type) &&
                Objects.equals(title, excelItem.title) &&
                Objects.equals(getMethodName, excelItem.getMethodName) &&
                Objects.equals(setMethodName, excelItem.setMethodName) &&
                Objects.equals(orderNum, excelItem.orderNum) &&
                Objects.equals(exportItemHandler, excelItem.exportItemHandler) &&
                Objects.equals(importItemHandler, excelItem.importItemHandler);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entity, entityName, name, type, title, getMethodName, setMethodName, exportItemHandler, importItemHandler);
    }

    @Override
    public String toString() {
        return "ESExcelItem{" +
                "entity='" + entity + '\'' +
                "entityName='" + entityName + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", getMethodName='" + getMethodName + '\'' +
                ", setMethodName='" + setMethodName + '\'' +
                ", orderNum='" + orderNum + '\'' +
                ", exportItemHandler='" + exportItemHandler + '\'' +
                ", importItemHandler='" + importItemHandler + '\'' +
                '}';
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGetMethodName() {
        return getMethodName;
    }

    public void setGetMethodName(String getMethodName) {
        this.getMethodName = getMethodName;
    }

    public String getSetMethodName() {
        return setMethodName;
    }

    public void setSetMethodName(String setMethodName) {
        this.setMethodName = setMethodName;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public ESExcelItemHandler getExportItemHandler() {
        return exportItemHandler;
    }

    public void setExportItemHandler(ESExcelItemHandler exportItemHandler) {
        this.exportItemHandler = exportItemHandler;
    }

    public ESExcelItemHandler getImportItemHandler() {
        return importItemHandler;
    }

    public void setImportItemHandler(ESExcelItemHandler importItemHandler) {
        this.importItemHandler = importItemHandler;
    }
}
