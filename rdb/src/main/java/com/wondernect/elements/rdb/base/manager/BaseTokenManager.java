package com.wondernect.elements.rdb.base.manager;

import com.wondernect.elements.rdb.base.dao.BaseTokenDao;
import com.wondernect.elements.rdb.base.model.BaseTokenModel;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: BaseTokenManager
 * Author: chenxun
 * Date: 2019/3/26 16:49
 * Description: base token manager
 */
public abstract class BaseTokenManager<T extends BaseTokenModel> extends BaseManager<T, String> {

    @Autowired
    private BaseTokenDao<T> baseTokenDao;

    public void deleteByToken(String token) {
        baseTokenDao.deleteByToken(token);
    }

    public T findByToken(String token) {
        return baseTokenDao.findByToken(token);
    }
}
