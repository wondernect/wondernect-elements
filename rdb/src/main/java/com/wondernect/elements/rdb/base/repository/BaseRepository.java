package com.wondernect.elements.rdb.base.repository;

import com.wondernect.elements.rdb.base.model.BaseModel;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * Copyright (C), 2019, wondernect.com
 * FileName: BaseRepository
 * Author: chenxun
 * Date: 2019-06-12 22:08
 * Description: 基础repository
 */
@NoRepositoryBean
public interface BaseRepository<T extends BaseModel, ID extends Serializable> extends BaseRDBRepository<T, ID> {

}
