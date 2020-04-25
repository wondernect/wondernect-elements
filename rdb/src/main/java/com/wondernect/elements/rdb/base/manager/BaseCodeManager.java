package com.wondernect.elements.rdb.base.manager;

import com.wondernect.elements.rdb.base.dao.BaseCodeDao;
import com.wondernect.elements.rdb.base.model.BaseCodeModel;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: BaseCodeManager
 * Author: chenxun
 * Date: 2019/3/26 16:55
 * Description: base code manager
 */
public abstract class BaseCodeManager<T extends BaseCodeModel> extends BaseManager<T, String> {

    @Autowired
    private BaseCodeDao<T> baseCodeDao;

    public void deleteByCode(String code) {
        baseCodeDao.deleteByCode(code);
    }

    public T findByCode(String code) {
        return baseCodeDao.findByCode(code);
    }
}
