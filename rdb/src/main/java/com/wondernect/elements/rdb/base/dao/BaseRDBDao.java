package com.wondernect.elements.rdb.base.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.rdb.base.model.BaseRDBModel;
import com.wondernect.elements.rdb.base.repository.BaseRDBRepository;
import com.wondernect.elements.rdb.common.error.RDBErrorEnum;
import com.wondernect.elements.rdb.common.exception.RDBException;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.request.PageRequestData;
import com.wondernect.elements.rdb.request.SortData;
import com.wondernect.elements.rdb.response.PageResponseData;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Copyright (C), 2017-2020, wondernect.com
 * FileName: BaseRDBDao
 * Author: chenxun
 * Date: 2020/1/19 9:17
 * Description: rdb dao
 */
public abstract class BaseRDBDao<T extends BaseRDBModel, ID extends Serializable> extends BasePageableDao {

    private static final Logger logger = LoggerFactory.getLogger(BaseRDBDao.class);

    @Autowired
    private BaseRDBRepository<T, ID> baseRDBRepository;

    @Autowired
    private EntityManager entityManager;

    /**
     * base JPA query factory
     */
    private JPAQueryFactory jpaQueryFactory;

    @PostConstruct
    public void initJPAQueryFactory() {
        jpaQueryFactory = new JPAQueryFactory(entityManager);
        logger.info("{} JPAQueryFactory init success", getClass().getSimpleName());
    }

    public JPAQueryFactory getJpaQueryFactory() {
        return jpaQueryFactory;
    }

    public T save(T entity) {
        T entitySave;
        try {
            entitySave = baseRDBRepository.save(entity);
        } catch (RuntimeException e) {
            logger.error("RDB数据保存失败:" + e.getLocalizedMessage(), e);
            throw new RDBException(RDBErrorEnum.RDB_SAVE_FAILED);
        }
        return entitySave;
    }

    public List<T> saveAll(List<T> entityList) {
        List<T> entityListSave = new ArrayList<>();
        if (CollectionUtils.isEmpty(entityList)) {
            return entityListSave;
        }
        try {
            entityListSave = baseRDBRepository.saveAll(entityList);
        } catch (RuntimeException e) {
            logger.error("RDB数据批量保存失败:" + e.getLocalizedMessage(), e);
            throw new RDBException(RDBErrorEnum.RDB_SAVE_ALL_FAILED);
        }
        return entityListSave;
    }

    public void deleteById(ID entityId) {
        if (ESObjectUtils.isNull(entityId)) {
            logger.error("RDB数据删除失败,删除对象id传入为空");
        } else {
            try {
                baseRDBRepository.deleteById(entityId);
            } catch (RuntimeException e) {
                logger.error("RDB数据删除失败:" + e.getLocalizedMessage(), e);
                throw new RDBException(RDBErrorEnum.RDB_DELETE_FAILED);
            }
        }
    }

    public void deleteAll() {
        try {
            baseRDBRepository.deleteAll();
        } catch (RuntimeException e) {
            logger.error("RDB数据全部删除失败:" + e.getLocalizedMessage(), e);
            throw new RDBException(RDBErrorEnum.RDB_DELETE_ALL_FAILED);
        }
    }

    public T findById(ID entityId) {
        if (ESObjectUtils.isNull(entityId)) {
            return null;
        }
        T entity = null;
        try {
            Optional<T> optionalT = baseRDBRepository.findById(entityId);
            if (optionalT.isPresent()) {
                entity = optionalT.get();
            }
        } catch (RuntimeException e) {
            logger.error("RDB数据获取失败:" + e.getLocalizedMessage(), e);
            throw new RDBException(RDBErrorEnum.RDB_GET_FAILED);
        }
        return entity;
    }

    public boolean existById(ID entityId) {
        if (ESObjectUtils.isNull(entityId)) {
            return false;
        }
        boolean exist;
        try {
            exist = baseRDBRepository.existsById(entityId);
        } catch (RuntimeException e) {
            logger.error("RDB数据判断是否存在失败:" + e.getLocalizedMessage(), e);
            throw new RDBException(RDBErrorEnum.RDB_EXIST_GET_FAILED);
        }
        return exist;
    }

    public long count() {
        long count;
        try {
            count = baseRDBRepository.count();
        } catch (RuntimeException e) {
            logger.error("RDB数据计数失败:" + e.getLocalizedMessage(), e);
            throw new RDBException(RDBErrorEnum.RDB_COUNT_GET_FAILED);
        }
        return count;
    }

    public long count(Criteria<T> criteria) {
        long count;
        try {
            count = baseRDBRepository.count(criteria);
        } catch (RuntimeException e) {
            logger.error("RDB数据组合查询计数失败:" + e.getLocalizedMessage(), e);
            throw new RDBException(RDBErrorEnum.RDB_COUNT_GET_FAILED);
        }
        return count;
    }

    public List<T> findAll(List<SortData> sortDataList) {
        List<T> list = new ArrayList<>();
        try {
            Iterable<T> iterable = baseRDBRepository.findAll(generateDefaultSort(sortDataList));
            if (ESObjectUtils.isNotNull(iterable)) {
                for (T t : iterable) {
                    list.add(t);
                }
            }
        } catch (RuntimeException e) {
            logger.error("RDB数据列表获取失败:" + e.getLocalizedMessage(), e);
            throw new RDBException(RDBErrorEnum.RDB_LIST_GET_FAILED);
        }
        return list;
    }

    public List<T> findAll(Criteria<T> criteria, List<SortData> sortDataList) {
        List<T> list;
        try {
            list = baseRDBRepository.findAll(criteria, generateDefaultSort(sortDataList));
        } catch (RuntimeException e) {
            logger.error("RDB数据组合查询列表获取失败:" + e.getLocalizedMessage(), e);
            throw new RDBException(RDBErrorEnum.RDB_LIST_GET_FAILED);
        }
        return list;
    }

    public PageResponseData<T> findAll(PageRequestData pageRequestData) {
        Page<T> page;
        try {
            page = baseRDBRepository.findAll(generateDefaultPageRequest(pageRequestData));
        } catch (RuntimeException e) {
            logger.error("RDB数据分页获取失败:" + e.getLocalizedMessage(), e);
            throw new RDBException(RDBErrorEnum.RDB_PAGE_GET_FAILED);
        }
        return new PageResponseData<>(pageRequestData.getPage(), pageRequestData.getSize(), page.getTotalPages(), page.getTotalElements(), page.getContent());
    }

    public PageResponseData<T> findAll(Criteria<T> criteria, PageRequestData pageRequestData) {
        Page<T> page;
        try {
            page = baseRDBRepository.findAll(criteria, generateDefaultPageRequest(pageRequestData));
        } catch (RuntimeException e) {
            logger.error("RDB数据组合查询分页获取失败:" + e.getLocalizedMessage(), e);
            throw new RDBException(RDBErrorEnum.RDB_PAGE_GET_FAILED);
        }
        return new PageResponseData<>(pageRequestData.getPage(), pageRequestData.getSize(), page.getTotalPages(), page.getTotalElements(), page.getContent());
    }
}
