package com.wondernect.elements.rdb.base.manager;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wondernect.elements.rdb.base.dao.BaseRDBDao;
import com.wondernect.elements.rdb.base.model.BaseRDBModel;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.request.PageRequestData;
import com.wondernect.elements.rdb.request.SortData;
import com.wondernect.elements.rdb.response.PageResponseData;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * Copyright (C), 2017-2020, wondernect.com
 * FileName: BaseRDBManager
 * Author: chenxun
 * Date: 2020/1/19 9:29
 * Description: rdb manager
 */
public abstract class BaseRDBManager<T extends BaseRDBModel, ID extends Serializable> {

    @Autowired
    private BaseRDBDao<T, ID> baseRDBDao;

    public JPAQueryFactory getJpaQueryFactory() {
        return baseRDBDao.getJpaQueryFactory();
    }

    public T save(T entity) {
        return baseRDBDao.save(entity);
    }

    public List<T> saveAll(List<T> entityList) {
        return baseRDBDao.saveAll(entityList);
    }

    public void deleteById(ID entityId) {
        baseRDBDao.deleteById(entityId);
    }

    public void deleteAll() {
        baseRDBDao.deleteAll();
    }

    public T findById(ID entityId) {
        return baseRDBDao.findById(entityId);
    }

    public boolean existById(ID entityId) {
        return baseRDBDao.existById(entityId);
    }

    public long count() {
        return baseRDBDao.count();
    }

    public long count(Criteria<T> criteria) {
        return baseRDBDao.count(criteria);
    }

    public List<T> findAll(List<SortData> sortDataList) {
        return baseRDBDao.findAll(sortDataList);
    }

    public List<T> findAll(Criteria<T> criteria, List<SortData> sortDataList) {
        return baseRDBDao.findAll(criteria, sortDataList);
    }

    public PageResponseData<T> findAll(PageRequestData pageRequestData) {
        return baseRDBDao.findAll(pageRequestData);
    }

    public PageResponseData<T> findAll(Criteria<T> criteria, PageRequestData pageRequestData) {
        return baseRDBDao.findAll(criteria, pageRequestData);
    }
}
