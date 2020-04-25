package com.wondernect.elements.rdb.base.dao;

import com.wondernect.elements.rdb.base.model.BaseCodeModel;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: BaseCodeDao
 * Author: chenxun
 * Date: 2019/3/15 10:01
 * Description:
 */
public abstract class BaseCodeDao<T extends BaseCodeModel> extends BaseDao<T, String> {

    public void deleteByCode(String code) {
        super.deleteById(code);
    }

    public T findByCode(String code) {
        return super.findById(code);
    }
}
