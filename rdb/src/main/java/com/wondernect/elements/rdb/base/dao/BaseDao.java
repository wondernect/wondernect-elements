package com.wondernect.elements.rdb.base.dao;

import com.querydsl.jpa.impl.JPAQuery;
import com.wondernect.elements.rdb.base.model.BaseModel;
import com.wondernect.elements.rdb.config.AppFilter;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.request.PageRequestData;
import com.wondernect.elements.rdb.request.SortData;
import com.wondernect.elements.rdb.response.PageResponseData;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Copyright (C), 2019, wondernect.com
 * FileName: BaseDao
 * Author: chenxun
 * Date: 2019-06-12 22:11
 * Description: 基础dao
 */
public abstract class BaseDao<T extends BaseModel, ID extends Serializable> extends BaseRDBDao<T, ID> {

    @AppFilter
    @Transactional
    @Override
    public void deleteAll() {
        super.deleteAll();
    }

    @AppFilter
    @Transactional
    @Override
    public long count() {
        return super.count();
    }

    @AppFilter
    @Transactional
    @Override
    public long count(Criteria<T> criteria) {
        return super.count(criteria);
    }

    @AppFilter
    @Transactional
    @Override
    public T findOne(List<SortData> sortDataList) {
        return super.findOne(sortDataList);
    }

    @AppFilter
    @Transactional
    @Override
    public T findOne(Criteria<T> criteria, List<SortData> sortDataList) {
        return super.findOne(criteria, sortDataList);
    }

    @AppFilter
    @Transactional
    @Override
    public List<T> findAll(List<SortData> sortDataList) {
        return super.findAll(sortDataList);
    }

    @AppFilter
    @Transactional
    @Override
    public List<T> findAll(Criteria<T> criteria, List<SortData> sortDataList) {
        return super.findAll(criteria, sortDataList);
    }

    @AppFilter
    @Transactional
    @Override
    public PageResponseData<T> findAll(PageRequestData pageRequestData) {
        return super.findAll(pageRequestData);
    }

    @AppFilter
    @Transactional
    @Override
    public PageResponseData<T> findAll(Criteria<T> criteria, PageRequestData pageRequestData) {
        return super.findAll(criteria, pageRequestData);
    }

    @AppFilter
    @Transactional
    @Override
    public <S> long count(JPAQuery<S> jpaQuery) {
        return super.count(jpaQuery);
    }

    @AppFilter
    @Transactional
    @Override
    public <S> List<S> findAll(JPAQuery<S> jpaQuery) {
        return super.findAll(jpaQuery);
    }

    @AppFilter
    @Transactional
    @Override
    public <S> S findOne(JPAQuery<S> jpaQuery) {
        return super.findOne(jpaQuery);
    }

    @AppFilter
    @Transactional
    @Override
    public <S> PageResponseData<S> findAll(JPAQuery<S> jpaQuery, PageRequestData pageRequestData) {
        return super.findAll(jpaQuery, pageRequestData);
    }
}
