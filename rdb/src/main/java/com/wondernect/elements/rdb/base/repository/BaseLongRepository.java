package com.wondernect.elements.rdb.base.repository;

import com.wondernect.elements.rdb.base.model.BaseLongModel;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: BaseLongRepository
 * Author: chenxun
 * Date: 2018/10/16 13:56
 * Description: Long id 基础数据访问对象
 */
@NoRepositoryBean
public interface BaseLongRepository<T extends BaseLongModel> extends BaseIDRepository<T, Long> {

}
