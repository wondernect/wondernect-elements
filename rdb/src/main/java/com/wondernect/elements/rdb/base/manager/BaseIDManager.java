package com.wondernect.elements.rdb.base.manager;

import com.wondernect.elements.rdb.base.model.BaseIDModel;

import java.io.Serializable;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: BaseIDManager
 * Author: chenxun
 * Date: 2019/8/10 9:22
 * Description: id manager
 */
public abstract class BaseIDManager<T extends BaseIDModel<ID>, ID extends Serializable> extends BaseManager<T, ID> {

}
