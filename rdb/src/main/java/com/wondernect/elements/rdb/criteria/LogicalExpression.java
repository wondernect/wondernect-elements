package com.wondernect.elements.rdb.criteria;

import com.wondernect.elements.rdb.criteria.em.LogicalOperator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: LogicalExpression
 * Author: chenxun
 * Date: 2019/4/25 11:35
 * Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogicalExpression implements Criterion {

    private Criterion[] criterions; // 逻辑表达式中包含的表达式

    private LogicalOperator operator; //计算符

    @Override
    public Predicate toPredicate(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        if (ArrayUtils.isNotEmpty(criterions)) {
            for (Criterion criterion : criterions) {
                predicates.add(criterion.toPredicate(root, query, builder));
            }
        }
        if (CollectionUtils.isNotEmpty(predicates)) {
            switch (operator) {
                case OR:
                    return builder.or(predicates.toArray(new Predicate[0]));
                case AND:
                    return builder.and(predicates.toArray(new Predicate[0]));
            }
        }
        return null;
    }
}
