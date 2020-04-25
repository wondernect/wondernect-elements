package com.wondernect.elements.rdb.criteria;

import com.wondernect.elements.rdb.criteria.em.SimpleOperator;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;

import javax.persistence.criteria.*;
import java.util.Collection;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: SimpleExpression
 * Author: chenxun
 * Date: 2019/4/25 11:31
 * Description:
 */
@Data
@AllArgsConstructor
public class SimpleExpression implements Criterion {

    private String property; //属性名

    private Object value; //对应值

    private Collection<?> values; // 对应值集合

    private SimpleOperator operator; //计算符

    public SimpleExpression() {
    }

    public SimpleExpression(String property, SimpleOperator operator) {
        this.property = property;
        this.operator = operator;
    }

    public SimpleExpression(String property, Object value, SimpleOperator operator) {
        this.property = property;
        this.value = value;
        this.operator = operator;
    }

    public SimpleExpression(String property, Collection<?> values, SimpleOperator operator) {
        this.property = property;
        this.values = values;
        this.operator = operator;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public Predicate toPredicate(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        Path expression = root.get(property);
        switch (operator) {
            case EQ:
                return builder.equal(expression, value);
            case NE:
                return builder.notEqual(expression, value);
            case LIKE:
                return builder.like(expression, value.toString());
            case NOT_LIKE:
                return builder.notLike(expression, value.toString());
            case IS_NULL:
                return builder.isNull(expression);
            case IS_NOT_NULL:
                return builder.isNotNull(expression);
            case LT:
                return builder.lessThan(expression, (Comparable) value);
            case GT:
                return builder.greaterThan(expression, (Comparable) value);
            case LTE:
                return builder.lessThanOrEqualTo(expression, (Comparable) value);
            case GTE:
                return builder.greaterThanOrEqualTo(expression, (Comparable) value);
            case IN:
                CriteriaBuilder.In<Object> in = builder.in(expression);
                if (CollectionUtils.isNotEmpty(values)) {
                    for (Object value: values) {
                        in.value(value);
                    }
                    return in;
                }
                return null;
            default:
                return null;
        }
    }
}
