package com.wondernect.elements.rdb.base.dao;

import com.wondernect.elements.rdb.base.model.BaseTokenModel;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: BaseTokenDao
 * Author: chenxun
 * Date: 2019/3/15 10:02
 * Description:
 */
public abstract class BaseTokenDao<T extends BaseTokenModel> extends BaseDao<T, String> {

    public void deleteByToken(String token) {
        super.deleteById(token);
    }

    public T findByToken(String token) {
        return super.findById(token);
    }
}
