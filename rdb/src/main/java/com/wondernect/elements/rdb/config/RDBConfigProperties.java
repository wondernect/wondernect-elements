package com.wondernect.elements.rdb.config;

import com.wondernect.elements.property.source.WondernectPropertySourceFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: NamingConfigProperties
 * Author: chenxun
 * Date: 2018/10/19 14:12
 * Description:
 */
@Component
@Primary
@PropertySource(value = {"classpath:application.properties", "classpath:application.yml", "classpath:application.yaml"}, ignoreResourceNotFound = true, factory = WondernectPropertySourceFactory.class)
@ConfigurationProperties(prefix = "wondernect.elements.rdb")
public class RDBConfigProperties implements Serializable {

    private static final long serialVersionUID = 2950916026001330897L;

    private String tablePrefix = ""; // 添加表名称前缀

    private List<String> tablePrefixBlackList; // 不添加表名称前缀的表名列表

    private int initPageSize = 10; // 当用户传入page size为空时初始化size为initPageSize

    private int limitPageSize = 500; // 当用户传入page size大于limitPageSize时,修改size为limitPageSize

    private String sortProperty = "createTime"; // 排序属性

    private String sortDirection = "DESC"; // 排序方式

    public String getTablePrefix() {
        return tablePrefix;
    }

    public void setTablePrefix(String tablePrefix) {
        this.tablePrefix = tablePrefix;
    }

    public List<String> getTablePrefixBlackList() {
        return tablePrefixBlackList;
    }

    public void setTablePrefixBlackList(List<String> tablePrefixBlackList) {
        this.tablePrefixBlackList = tablePrefixBlackList;
    }

    public int getInitPageSize() {
        return initPageSize;
    }

    public void setInitPageSize(int initPageSize) {
        this.initPageSize = initPageSize;
    }

    public int getLimitPageSize() {
        return limitPageSize;
    }

    public void setLimitPageSize(int limitPageSize) {
        this.limitPageSize = limitPageSize;
    }

    public String getSortProperty() {
        return sortProperty;
    }

    public void setSortProperty(String sortProperty) {
        this.sortProperty = sortProperty;
    }

    public String getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }
}
