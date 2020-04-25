package com.wondernect.elements.rdb.base.repository;

import com.wondernect.elements.rdb.base.model.BaseIDModel;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: BaseIDRepository
 * Author: chenxun
 * Date: 2019/8/10 9:24
 * Description: id repository
 */
@NoRepositoryBean
public interface BaseIDRepository<T extends BaseIDModel<ID>, ID extends Serializable> extends BaseRepository<T, ID> {

}
