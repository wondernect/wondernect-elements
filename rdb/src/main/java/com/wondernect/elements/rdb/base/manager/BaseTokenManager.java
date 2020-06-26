package com.wondernect.elements.rdb.base.manager;

import com.wondernect.elements.rdb.base.model.BaseTokenModel;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: BaseTokenManager
 * Author: chenxun
 * Date: 2019/3/26 16:49
 * Description: base token manager
 */
public abstract class BaseTokenManager<T extends BaseTokenModel> extends BaseManager<T, String> {

    public void deleteByToken(String token) {
        super.deleteById(token);
    }

    public T findByToken(String token) {
        return super.findById(token);
    }
}
