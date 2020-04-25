package com.wondernect.elements.rdb.base.repository;

import com.wondernect.elements.rdb.base.model.BaseCodeModel;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: BaseUUIDRepository
 * Author: chenxun
 * Date: 2018/10/16 14:01
 * Description: UUID code 基础数据访问对象
 */
@NoRepositoryBean
public interface BaseCodeRepository<T extends BaseCodeModel> extends BaseRepository<T, String> {

}
