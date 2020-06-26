package com.wondernect.elements.rdb.base.service;

import com.wondernect.elements.rdb.base.model.BaseIDModel;

import java.io.Serializable;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: BaseIDService
 * Author: chenxun
 * Date: 2020-06-26 08:19
 * Description:
 */
public abstract class BaseIDService<RES_DTO, T extends BaseIDModel<ID>, ID extends Serializable> extends BaseService<RES_DTO, T, ID> {

}
