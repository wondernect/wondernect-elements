package com.wondernect.elements.rdb.base.service;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.rdb.base.manager.BaseRDBManager;
import com.wondernect.elements.rdb.base.model.BaseRDBModel;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.request.PageRequestData;
import com.wondernect.elements.rdb.request.SortData;
import com.wondernect.elements.rdb.response.PageResponseData;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: BaseRDBService
 * Author: chenxun
 * Date: 2020-06-26 07:55
 * Description:
 */
public abstract class BaseRDBService<RES_DTO, T extends BaseRDBModel, ID extends Serializable> {

    @Autowired
    private BaseRDBManager<T, ID> baseRDBManager;

    public JPAQueryFactory getJpaQueryFactory() {
        return baseRDBManager.getJpaQueryFactory();
    }

    public T saveEntity(T entity) {
        return baseRDBManager.save(entity);
    }

    public RES_DTO save(T entity) {
        T entitySave = baseRDBManager.save(entity);
        return generate(entitySave);
    }

    public List<T> saveAllEntity(List<T> entityList) {
        return baseRDBManager.saveAll(entityList);
    }

    public List<RES_DTO> saveAll(List<T> entityList) {
        List<T> list = baseRDBManager.saveAll(entityList);
        List<RES_DTO> resDtoList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (T entity : list) {
                resDtoList.add(generate(entity));
            }
        }
        return resDtoList;
    }

    public void deleteById(ID entityId) {
        T entity = baseRDBManager.findById(entityId);
        if (ESObjectUtils.isNotNull(entity)) {
            baseRDBManager.deleteById(entityId);
        }
    }

    public void deleteAll() {
        baseRDBManager.deleteAll();
    }

    public T findEntityById(ID entityId) {
        T entity = baseRDBManager.findById(entityId);
        if (ESObjectUtils.isNull(entity)) {
            return null;
        }
        return entity;
    }

    public RES_DTO findById(ID entityId) {
        T entity = baseRDBManager.findById(entityId);
        if (ESObjectUtils.isNull(entity)) {
            return null;
        }
        return generate(entity);
    }

    public boolean existById(ID entityId) {
        return baseRDBManager.existById(entityId);
    }

    public long count() {
        return baseRDBManager.count();
    }

    public long count(Criteria<T> criteria) {
        return baseRDBManager.count(criteria);
    }

    public List<T> findAllEntity(List<SortData> sortDataList) {
        return baseRDBManager.findAll(sortDataList);
    }

    public List<T> findAllEntity(Criteria<T> criteria, List<SortData> sortDataList) {
        return baseRDBManager.findAll(criteria, sortDataList);
    }

    public T findOneEntity(List<SortData> sortDataList) {
        return baseRDBManager.findOne(sortDataList);
    }

    public T findOneEntity(Criteria<T> criteria, List<SortData> sortDataList) {
        return baseRDBManager.findOne(criteria, sortDataList);
    }

    public List<RES_DTO> findAll(List<SortData> sortDataList) {
        List<T> list = baseRDBManager.findAll(sortDataList);
        List<RES_DTO> resDtoList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (T entity : list) {
                resDtoList.add(generate(entity));
            }
        }
        return resDtoList;
    }

    public List<RES_DTO> findAll(Criteria<T> criteria, List<SortData> sortDataList) {
        List<T> list = baseRDBManager.findAll(criteria, sortDataList);
        List<RES_DTO> resDtoList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (T entity : list) {
                resDtoList.add(generate(entity));
            }
        }
        return resDtoList;
    }

    public RES_DTO findOne(List<SortData> sortDataList) {
        List<T> list = baseRDBManager.findAll(sortDataList);
        List<RES_DTO> resDtoList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (T entity : list) {
                resDtoList.add(generate(entity));
            }
        }
        if (CollectionUtils.isEmpty(resDtoList)) {
            return null;
        }
        return resDtoList.get(0);
    }

    public RES_DTO findOne(Criteria<T> criteria, List<SortData> sortDataList) {
        List<T> list = baseRDBManager.findAll(criteria, sortDataList);
        List<RES_DTO> resDtoList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (T entity : list) {
                resDtoList.add(generate(entity));
            }
        }
        if (CollectionUtils.isEmpty(resDtoList)) {
            return null;
        }
        return resDtoList.get(0);
    }

    public PageResponseData<T> findAllEntity(PageRequestData pageRequestData) {
        return baseRDBManager.findAll(pageRequestData);
    }

    public PageResponseData<T> findAllEntity(Criteria<T> criteria, PageRequestData pageRequestData) {
        return baseRDBManager.findAll(criteria, pageRequestData);
    }

    public PageResponseData<RES_DTO> findAll(PageRequestData pageRequestData) {
        PageResponseData<T> pageResponseData = baseRDBManager.findAll(pageRequestData);
        List<T> list = pageResponseData.getDataList();
        List<RES_DTO> resDtoList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (T entity : list) {
                resDtoList.add(generate(entity));
            }
        }
        return new PageResponseData<>(
                pageResponseData.getPage(),
                pageResponseData.getSize(),
                pageResponseData.getTotalPages(),
                pageResponseData.getTotalElements(),
                resDtoList
        );
    }

    public PageResponseData<RES_DTO> findAll(Criteria<T> criteria, PageRequestData pageRequestData) {
        PageResponseData<T> pageResponseData = baseRDBManager.findAll(criteria, pageRequestData);
        List<T> list = pageResponseData.getDataList();
        List<RES_DTO> resDtoList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (T entity : list) {
                resDtoList.add(generate(entity));
            }
        }
        return new PageResponseData<>(
                pageResponseData.getPage(),
                pageResponseData.getSize(),
                pageResponseData.getTotalPages(),
                pageResponseData.getTotalElements(),
                resDtoList
        );
    }

    public <S> long count(JPAQuery<S> jpaQuery) {
        return baseRDBManager.count();
    }

    public <S> List<S> findAllEntity(JPAQuery<S> jpaQuery) {
        return baseRDBManager.findAll(jpaQuery);
    }

    public List<RES_DTO> findAll(JPAQuery<T> jpaQuery) {
        List<T> list = baseRDBManager.findAll(jpaQuery);
        List<RES_DTO> resDtoList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (T entity : list) {
                resDtoList.add(generate(entity));
            }
        }
        return resDtoList;
    }

    public <S> S findOneEntity(JPAQuery<S> jpaQuery) {
        return baseRDBManager.findOne(jpaQuery);
    }

    public <S> PageResponseData<S> findAllEntity(JPAQuery<S> jpaQuery, PageRequestData pageRequestData) {
        return baseRDBManager.findAll(jpaQuery, pageRequestData);
    }

    public PageResponseData<RES_DTO> findAll(JPAQuery<T> jpaQuery, PageRequestData pageRequestData) {
        PageResponseData<T> pageResponseData = baseRDBManager.findAll(jpaQuery, pageRequestData);
        List<T> list = pageResponseData.getDataList();
        List<RES_DTO> resDtoList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (T entity : list) {
                resDtoList.add(generate(entity));
            }
        }
        return new PageResponseData<>(
                pageResponseData.getPage(),
                pageResponseData.getSize(),
                pageResponseData.getTotalPages(),
                pageResponseData.getTotalElements(),
                resDtoList
        );
    }

    public abstract RES_DTO generate(T entity);
}
