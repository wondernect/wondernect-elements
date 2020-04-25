package com.wondernect.elements.rdb.base.repository;

import com.wondernect.elements.rdb.base.model.BaseStringModel;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: BaseStringRepository
 * Author: chenxun
 * Date: 2018/10/12 14:50
 * Description: String id 基础数据访问对象
 */
@NoRepositoryBean
public interface BaseStringRepository<T extends BaseStringModel> extends BaseIDRepository<T, String> {

}
