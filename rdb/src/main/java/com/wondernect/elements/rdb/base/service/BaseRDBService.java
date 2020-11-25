package com.wondernect.elements.rdb.base.service;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.rdb.base.manager.BaseRDBManager;
import com.wondernect.elements.rdb.base.model.BaseRDBModel;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.request.PageRequestData;
import com.wondernect.elements.rdb.request.SortData;
import com.wondernect.elements.rdb.response.BaseRDBResponseDTO;
import com.wondernect.elements.rdb.response.PageResponseData;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public abstract class BaseRDBService<RES_DTO extends BaseRDBResponseDTO, T extends BaseRDBModel, ID extends Serializable> {

    private static final Logger logger = LoggerFactory.getLogger(BaseRDBService.class);

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

    public long count(JPAQuery<T> jpaQuery) {
        return baseRDBManager.count(jpaQuery);
    }

    public List<T> findAllEntity(List<SortData> sortDataList) {
        return baseRDBManager.findAll(sortDataList);
    }

    public List<T> findAllEntity(Criteria<T> criteria, List<SortData> sortDataList) {
        return baseRDBManager.findAll(criteria, sortDataList);
    }

    public List<T> findAllEntity(JPAQuery<T> jpaQuery) {
        return baseRDBManager.findAll(jpaQuery);
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

    public T findOneEntity(List<SortData> sortDataList) {
        return baseRDBManager.findOne(sortDataList);
    }

    public T findOneEntity(Criteria<T> criteria, List<SortData> sortDataList) {
        return baseRDBManager.findOne(criteria, sortDataList);
    }

    public T findOneEntity(JPAQuery<T> jpaQuery) {
        return baseRDBManager.findOne(jpaQuery);
    }

    public RES_DTO findOne(List<SortData> sortDataList) {
        T entity = baseRDBManager.findOne(sortDataList);
        if (ESObjectUtils.isNull(entity)) {
            return null;
        }
        return generate(entity);
    }

    public RES_DTO findOne(Criteria<T> criteria, List<SortData> sortDataList) {
        T entity = baseRDBManager.findOne(criteria, sortDataList);
        if (ESObjectUtils.isNull(entity)) {
            return null;
        }
        return generate(entity);
    }

    public RES_DTO findOne(JPAQuery<T> jpaQuery) {
        T entity = baseRDBManager.findOne(jpaQuery);
        if (ESObjectUtils.isNull(entity)) {
            return null;
        }
        return generate(entity);
    }

    public PageResponseData<T> findAllEntity(PageRequestData pageRequestData) {
        return baseRDBManager.findAll(pageRequestData);
    }

    public PageResponseData<T> findAllEntity(Criteria<T> criteria, PageRequestData pageRequestData) {
        return baseRDBManager.findAll(criteria, pageRequestData);
    }

    public PageResponseData<T> findAllEntity(JPAQuery<T> jpaQuery, PageRequestData pageRequestData) {
        return baseRDBManager.findAll(jpaQuery, pageRequestData);
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

    public RES_DTO generate(T entity) {
        return null;
    }
}
