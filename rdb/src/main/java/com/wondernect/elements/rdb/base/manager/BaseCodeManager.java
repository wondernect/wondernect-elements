package com.wondernect.elements.rdb.base.manager;

import com.wondernect.elements.rdb.base.model.BaseCodeModel;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: BaseCodeManager
 * Author: chenxun
 * Date: 2019/3/26 16:55
 * Description: base code manager
 */
public abstract class BaseCodeManager<T extends BaseCodeModel> extends BaseManager<T, String> {

    public void deleteByCode(String code) {
        super.deleteById(code);
    }

    public T findByCode(String code) {
        return super.findById(code);
    }
}
