package com.wondernect.elements.rdb.base.dao;

import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.rdb.common.response.JPAQueryPageRequest;
import com.wondernect.elements.rdb.config.RDBConfigProperties;
import com.wondernect.elements.rdb.request.PageRequestData;
import com.wondernect.elements.rdb.request.SortData;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: SimplePageableDao
 * Author: chenxun
 * Date: 2019/3/14 17:50
 * Description:
 */
public abstract class BasePageableDao {

    @Autowired
    private RDBConfigProperties rdbConfigProperties;

    public Sort generateDefaultSort(List<SortData> sortDataList) {
        List<Sort.Order> orders = new ArrayList<>();
        if (CollectionUtils.isEmpty(sortDataList)) {
            sortDataList = new ArrayList<>();
            sortDataList.add(new SortData(rdbConfigProperties.getSortProperty(), rdbConfigProperties.getSortDirection()));
        }
        for (SortData sortData : sortDataList) {
            orders.add(new Sort.Order(Sort.Direction.valueOf(sortData.getDirection()), sortData.getProperty()));
        }
        return Sort.by(orders);
    }

    public Sort generateSort(List<SortData> sortDataList) {
        List<Sort.Order> orders = new ArrayList<>();
        if (CollectionUtils.isEmpty(sortDataList)) {
            return Sort.unsorted();
        }
        for (SortData sortData : sortDataList) {
            orders.add(new Sort.Order(Sort.Direction.valueOf(sortData.getDirection()), sortData.getProperty()));
        }
        return Sort.by(orders);
    }

    public PageRequest generateDefaultPageRequest(PageRequestData pageRequestData) {
        if (ESObjectUtils.isNull(pageRequestData)) {
            return PageRequest.of(0, rdbConfigProperties.getInitPageSize(), generateDefaultSort(null));
        }
        if (pageRequestData.getPage() < 0) {
            pageRequestData.setPage(0);
        }
        if (pageRequestData.getSize() <= 0) {
            pageRequestData.setSize(rdbConfigProperties.getInitPageSize());
        } else {
            if (pageRequestData.getSize() > rdbConfigProperties.getLimitPageSize()) {
                pageRequestData.setSize(rdbConfigProperties.getLimitPageSize());
            }
        }
        return PageRequest.of(pageRequestData.getPage(), pageRequestData.getSize(), generateDefaultSort(pageRequestData.getSortDataList()));
    }

    public PageRequest generatePageRequest(PageRequestData pageRequestData) {
        if (ESObjectUtils.isNull(pageRequestData)) {
            return PageRequest.of(0, rdbConfigProperties.getInitPageSize(), generateSort(null));
        }
        if (pageRequestData.getPage() < 0) {
            pageRequestData.setPage(0);
        }
        if (pageRequestData.getSize() <= 0) {
            pageRequestData.setSize(rdbConfigProperties.getInitPageSize());
        } else {
            if (pageRequestData.getSize() > rdbConfigProperties.getLimitPageSize()) {
                pageRequestData.setSize(rdbConfigProperties.getLimitPageSize());
            }
        }
        return PageRequest.of(pageRequestData.getPage(), pageRequestData.getSize(), generateSort(pageRequestData.getSortDataList()));
    }

    public JPAQueryPageRequest generateJPAQueryPageRequest(PageRequestData pageRequestData) {
        if (ESObjectUtils.isNull(pageRequestData)) {
            return new JPAQueryPageRequest(0, rdbConfigProperties.getInitPageSize());
        }
        if (pageRequestData.getPage() < 0) {
            pageRequestData.setPage(0);
        }
        if (pageRequestData.getSize() <= 0) {
            pageRequestData.setSize(rdbConfigProperties.getInitPageSize());
        } else {
            if (pageRequestData.getSize() > rdbConfigProperties.getLimitPageSize()) {
                pageRequestData.setSize(rdbConfigProperties.getLimitPageSize());
            }
        }
        int offset = pageRequestData.getPage() * pageRequestData.getSize();
        int limit = pageRequestData.getSize();
        return new JPAQueryPageRequest(offset, limit);
    }
}
