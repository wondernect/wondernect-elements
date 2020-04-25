package com.wondernect.elements.rdb.base.manager;

import com.wondernect.elements.rdb.base.dao.BaseSortDao;
import com.wondernect.elements.rdb.base.model.BaseSortModel;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.request.PageRequestData;
import com.wondernect.elements.rdb.request.SortData;
import com.wondernect.elements.rdb.response.PageResponseData;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: BaseStringManager
 * Author: chenxun
 * Date: 2019/3/26 16:36
 * Description: base string manager
 */
public abstract class BaseSortManager<T extends BaseSortModel<ID>, ID extends Serializable> extends BaseIDManager<T, ID> {

    @Autowired
    private BaseSortDao<T, ID> baseSortDao;

    public List<T> findAll(List<SortData> sortDataList) {
        return baseSortDao.findAll(sortDataList);
    }

    public List<T> findAll(Criteria<T> criteria, List<SortData> sortDataList) {
        return baseSortDao.findAll(criteria, sortDataList);
    }

    public PageResponseData<T> findAll(PageRequestData pageRequestData) {
        return baseSortDao.findAll(pageRequestData);
    }

    public PageResponseData<T> findAll(Criteria<T> criteria, PageRequestData pageRequestData) {
        return baseSortDao.findAll(criteria, pageRequestData);
    }
}
