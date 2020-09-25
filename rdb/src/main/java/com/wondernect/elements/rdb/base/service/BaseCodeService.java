package com.wondernect.elements.rdb.base.service;

import com.wondernect.elements.rdb.base.model.BaseCodeModel;
import com.wondernect.elements.rdb.response.BaseCodeResponseDTO;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: BaseCodeService
 * Author: chenxun
 * Date: 2020-06-26 08:33
 * Description:
 */
public abstract class BaseCodeService<RES_DTO extends BaseCodeResponseDTO, T extends BaseCodeModel> extends BaseService<RES_DTO, T, String> {

    public void deleteByCode(String code) {
        super.deleteById(code);
    }

    public T findEntityByCode(String code) {
        return super.findEntityById(code);
    }

    public RES_DTO findByCode(String code) {
        return super.findById(code);
    }
}
