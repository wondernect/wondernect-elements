package com.wondernect.elements.rdb.base.manager;

import com.wondernect.elements.rdb.base.model.BaseModel;

import java.io.Serializable;

/**
 * Copyright (C), 2019, wondernect.com
 * FileName: BaseManager
 * Author: chenxun
 * Date: 2019-06-12 22:40
 * Description: 基础manager
 */
public abstract class BaseManager<T extends BaseModel, ID extends Serializable> extends BaseRDBManager<T, ID> {

}
