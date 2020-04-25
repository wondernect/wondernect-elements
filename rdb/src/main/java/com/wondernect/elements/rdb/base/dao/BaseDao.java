package com.wondernect.elements.rdb.base.dao;

import com.wondernect.elements.rdb.base.model.BaseModel;

import java.io.Serializable;

/**
 * Copyright (C), 2019, wondernect.com
 * FileName: BaseDao
 * Author: chenxun
 * Date: 2019-06-12 22:11
 * Description: 基础dao
 */
public abstract class BaseDao<T extends BaseModel, ID extends Serializable> extends BaseRDBDao<T, ID> {


}
