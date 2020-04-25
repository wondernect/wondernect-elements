package com.wondernect.elements.rdb.base.dao;

import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.rdb.base.model.BaseSortModel;
import com.wondernect.elements.rdb.common.error.RDBErrorEnum;
import com.wondernect.elements.rdb.common.exception.RDBException;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.request.PageRequestData;
import com.wondernect.elements.rdb.request.SortData;
import com.wondernect.elements.rdb.response.PageResponseData;
import org.apache.commons.collections4.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: SimpleBaseStringDao
 * Author: chenxun
 * Date: 2019/3/14 18:25
 * Description:
 */
public abstract class BaseSortDao<T extends BaseSortModel<ID>, ID extends Serializable> extends BaseIDDao<T, ID> {

    public List<T> findAll(List<SortData> sortDataList) {
        if (CollectionUtils.isEmpty(sortDataList)) {
            sortDataList = new ArrayList<>();
            // 添加默认权重排序
            sortDataList.add(new SortData("weight", "DESC"));
        }
        return super.findAll(sortDataList);
    }

    public List<T> findAll(Criteria<T> criteria, List<SortData> sortDataList) {
        if (CollectionUtils.isEmpty(sortDataList)) {
            sortDataList = new ArrayList<>();
            // 添加默认权重排序
            sortDataList.add(new SortData("weight", "DESC"));
        }
        return super.findAll(criteria, sortDataList);
    }

    public PageResponseData<T> findAll(PageRequestData pageRequestData) {
        if (ESObjectUtils.isNull(pageRequestData)) {
            throw new RDBException(RDBErrorEnum.RDB_PAGE_REQUEST_DATA_CAN_NOT_NULL);
        }
        if (CollectionUtils.isEmpty(pageRequestData.getSortDataList())) {
            List<SortData> sortDataList = new ArrayList<>();
            sortDataList.add(new SortData("weight", "DESC"));
            // 添加默认权重排序
            pageRequestData.setSortDataList(sortDataList);
        }
        return super.findAll(pageRequestData);
    }

    public PageResponseData<T> findAll(Criteria<T> criteria, PageRequestData pageRequestData) {
        if (ESObjectUtils.isNull(pageRequestData)) {
            throw new RDBException(RDBErrorEnum.RDB_PAGE_REQUEST_DATA_CAN_NOT_NULL);
        }
        if (CollectionUtils.isEmpty(pageRequestData.getSortDataList())) {
            List<SortData> sortDataList = new ArrayList<>();
            sortDataList.add(new SortData("weight", "DESC"));
            // 添加默认权重排序
            pageRequestData.setSortDataList(sortDataList);
        }
        return super.findAll(criteria, pageRequestData);
    }
}
