package com.wondernect.elements.rdb.base.dao;

import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.rdb.base.model.BaseIDModel;

import java.io.Serializable;
import java.util.List;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: BaseIDDao
 * Author: chenxun
 * Date: 2019/8/10 9:19
 * Description: id dao
 */
public abstract class BaseIDDao<T extends BaseIDModel<ID>, ID extends Serializable> extends BaseDao<T, ID> {

    public T save(T entity) {
        if (ESObjectUtils.isNull(entity.getId())) {
            entity.setId(generateIdentifier());
        }
        return super.save(entity);
    }

    public List<T> saveAll(List<T> entityList) {
        for (T entity : entityList) {
            if (ESObjectUtils.isNull(entity.getId())) {
                entity.setId(generateIdentifier());
            }
        }
        return super.saveAll(entityList);
    }

    public abstract ID generateIdentifier();
}
