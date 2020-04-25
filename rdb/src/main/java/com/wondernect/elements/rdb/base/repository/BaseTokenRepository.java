package com.wondernect.elements.rdb.base.repository;

import com.wondernect.elements.rdb.base.model.BaseTokenModel;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: BaseUUIDRepository
 * Author: chenxun
 * Date: 2018/10/16 14:01
 * Description: UUID token 基础数据访问对象
 */
@NoRepositoryBean
public interface BaseTokenRepository<T extends BaseTokenModel> extends BaseRepository<T, String> {

}
