package com.wondernect.elements.rdb.criteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: Criterion
 * Author: chenxun
 * Date: 2019/4/25 11:26
 * Description:
 */
public interface Criterion {

    Predicate toPredicate(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder builder);
}
