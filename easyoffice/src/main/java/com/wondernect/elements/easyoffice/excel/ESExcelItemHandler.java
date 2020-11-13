package com.wondernect.elements.easyoffice.excel;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: ESExcelItemHandler
 * Author: chenxun
 * Date: 2020-07-22 00:37
 * Description: excel数据处理类
 * 1、需要展示的属性名需要继承该接口进行展示配置
 * 2、需要处理展示数据的属性名才需要继承该接口进行数据转换
 */
public interface ESExcelItemHandler<T> {

    /**
     * 返回该handler处理的属性名
     * @return 属性名
     */
    String itemName();

    /**
     * excel列展示名称
     * @return excel列展示名称
     */
    String itemTitle();

    /**
     * excel列展示顺序
     * @return 序号
     */
    int itemOrder();

    /**
     * 导出处理对应属性值，返回展示的值
     * @param object 属性原始值
     * @return 处理后excel展示的值
     */
    Object handleExcelExportItemObject(T object);

    /**
     * 导入处理对应属性值，返回对应对象属性类型值
     * @param object 属性原始值
     * @return 处理后导入数据库对象的属性值
     */
    T handleExcelImportItemObject(Object object);
}
