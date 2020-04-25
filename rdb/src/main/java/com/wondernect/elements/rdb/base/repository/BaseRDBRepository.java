package com.wondernect.elements.rdb.base.repository;

import com.wondernect.elements.rdb.base.model.BaseRDBModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * Copyright (C), 2017-2020, wondernect.com
 * FileName: BaseRDBRepository
 * Author: chenxun
 * Date: 2020/1/19 9:12
 * Description: rdb repository
 */
@NoRepositoryBean
public interface BaseRDBRepository<T extends BaseRDBModel, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T>, QuerydslPredicateExecutor<T> {

}
