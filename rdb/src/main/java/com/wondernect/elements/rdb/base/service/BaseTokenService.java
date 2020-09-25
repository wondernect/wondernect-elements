package com.wondernect.elements.rdb.base.service;

import com.wondernect.elements.rdb.base.model.BaseTokenModel;
import com.wondernect.elements.rdb.response.BaseTokenResponseDTO;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: BaseTokenService
 * Author: chenxun
 * Date: 2020-06-26 08:33
 * Description:
 */
public abstract class BaseTokenService<RES_DTO extends BaseTokenResponseDTO, T extends BaseTokenModel> extends BaseService<RES_DTO, T, String> {

    public void deleteByToken(String token) {
        super.deleteById(token);
    }

    public T findEntityByToken(String token) {
        return super.findEntityById(token);
    }

    public RES_DTO findByToken(String token) {
        return super.findById(token);
    }
}
