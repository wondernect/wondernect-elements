package com.wondernect.elements.rdb.base.service;

import com.wondernect.elements.rdb.base.model.BaseModel;
import com.wondernect.elements.rdb.response.BaseResponseDTO;

import java.io.Serializable;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: BaseService
 * Author: chenxun
 * Date: 2020-06-26 08:17
 * Description:
 */
public abstract class BaseService<RES_DTO extends BaseResponseDTO, T extends BaseModel, ID extends Serializable> extends BaseRDBService<RES_DTO, T, ID> {

}
