package com.wondernect.elements.rdb.criteria;

import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.rdb.criteria.em.LogicalOperator;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: Criteria
 * Author: chenxun
 * Date: 2019/4/25 11:25
 * Description:
 */
@Data
@AllArgsConstructor
public class Criteria<T> implements Specification<T> {

    private List<Criterion> criterions = new ArrayList<>();

    private LogicalOperator operator;

    private String distinctColumnName;

    public Criteria() {
        this.operator = LogicalOperator.AND;
        this.distinctColumnName = null;
    }

    public Criteria(LogicalOperator operator) {
        this.operator = operator;
        this.distinctColumnName = null;
    }

    public Criteria(String distinctColumnName) {
        this.operator = LogicalOperator.AND;
        this.distinctColumnName = distinctColumnName;
    }

    public Criteria(LogicalOperator operator, String distinctColumnName) {
        this.operator = operator;
        this.distinctColumnName = distinctColumnName;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (ESObjectUtils.isNotNull(distinctColumnName)) {
            query.select(root.get(distinctColumnName)).distinct(true);
        }
        if (CollectionUtils.isNotEmpty(criterions)) {
            List<Predicate> predicates = new ArrayList<>();
            for(Criterion criterion : criterions){
                predicates.add(criterion.toPredicate(root, query, criteriaBuilder));
            }
            if (CollectionUtils.isNotEmpty(predicates)) {
                switch (operator) {
                    case AND:
                    {
                        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
                    }
                    case OR:
                    {
                        return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
                    }
                }
            }
        }
        return criteriaBuilder.conjunction();
    }

    /**
     * 增加条件表达式
     */
    public void add(Criterion criterion){
        if(criterion != null){
            criterions.add(criterion);
        }
    }
}
