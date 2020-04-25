package com.wondernect.elements.rdb.base.repository;

import com.wondernect.elements.rdb.base.model.BaseSortModel;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: BaseSortRepository
 * Author: chenxun
 * Date: 2018/10/12 14:50
 * Description: String id 基础数据访问对象
 */
@NoRepositoryBean
public interface BaseSortRepository<T extends BaseSortModel<ID>, ID extends Serializable> extends BaseIDRepository<T, ID> {

}
