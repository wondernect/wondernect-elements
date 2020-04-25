package com.wondernect.elements.log.repository;

import com.wondernect.elements.log.model.BaseLogModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: BaseLogRepository
 * Author: chenxun
 * Date: 2018/11/30 17:07
 * Description:
 */
@NoRepositoryBean
public interface BaseLogRepository<T extends BaseLogModel> extends PagingAndSortingRepository<T, Long>, JpaSpecificationExecutor<T> {

}
