package com.wondernect.elements.rdb.criteria;

import com.wondernect.elements.common.utils.ESCollectionUtils;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.common.utils.ESStringUtils;
import com.wondernect.elements.rdb.criteria.em.LogicalOperator;
import com.wondernect.elements.rdb.criteria.em.SimpleOperator;
import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.criterion.MatchMode;

import java.util.Collection;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: Restrictions
 * Author: chenxun
 * Date: 2019/4/25 11:41
 * Description:
 */
public class Restrictions {

    /**
     * 等于
     */
    public static SimpleExpression eq(String property, Object value) {
        if(ESObjectUtils.isNull(value)) {
            return null;
        }
        return new SimpleExpression(property, value, SimpleOperator.EQ);
    }

    /**
     * 不等于
     */
    public static SimpleExpression ne(String property, Object value) {
        if(ESObjectUtils.isNull(value)) {
            return null;
        }
        return new SimpleExpression(property, value, SimpleOperator.NE);
    }

    /**
     * 模糊匹配 like
     */
    public static SimpleExpression like(String property, String value, MatchMode matchMode) {
        if(ESStringUtils.isBlank(value)) {
            return null;
        }
        return new SimpleExpression(property, matchMode.toMatchString(value), SimpleOperator.LIKE);
    }

    /**
     * 模糊匹配 not like
     */
    public static SimpleExpression notLike(String property, String value, MatchMode matchMode) {
        if(ESStringUtils.isBlank(value)) {
            return null;
        }
        return new SimpleExpression(property, matchMode.toMatchString(value), SimpleOperator.NOT_LIKE);
    }

    /**
     * null判断
     */
    public static SimpleExpression isNull(String property) {
        return new SimpleExpression(property, SimpleOperator.IS_NULL);
    }

    /**
     * not null判断
     */
    public static SimpleExpression isNotNull(String property) {
        return new SimpleExpression(property, SimpleOperator.IS_NOT_NULL);
    }

    /**
     * 大于
     */
    public static SimpleExpression gt(String property, Object value) {
        if(ESObjectUtils.isNull(value)) {
            return null;
        }
        return new SimpleExpression(property, value, SimpleOperator.GT);
    }

    /**
     * 小于
     */
    public static SimpleExpression lt(String property, Object value) {
        if(ESObjectUtils.isNull(value)) {
            return null;
        }
        return new SimpleExpression(property, value, SimpleOperator.LT);
    }

    /**
     * 大于等于
     */
    public static SimpleExpression gte(String property, Object value) {
        if(ESObjectUtils.isNull(value)) {
            return null;
        }
        return new SimpleExpression(property, value, SimpleOperator.GTE);
    }

    /**
     * 小于等于
     */
    public static SimpleExpression lte(String property, Object value) {
        if(ESObjectUtils.isNull(value)) {
            return null;
        }
        return new SimpleExpression(property, value, SimpleOperator.LTE);
    }

    /**
     * 包含
     */
    public static SimpleExpression in(String property, Collection<?> values) {
        if (CollectionUtils.isEmpty(values)) {
            return null;
        }
        return new SimpleExpression(property, values, SimpleOperator.IN);
    }

    /**
     * 并且
     */
    public static LogicalExpression and(Criterion... criterions) {
        Criterion[] array = ESCollectionUtils.excludeNull(criterions, new Criterion[0]);
        if (ESObjectUtils.isNull(array)) {
            return null;
        }
        return new LogicalExpression(array, LogicalOperator.AND);
    }

    /**
     * 或者
     */
    public static LogicalExpression or(Criterion... criterions) {
        Criterion[] array = ESCollectionUtils.excludeNull(criterions, new Criterion[0]);
        if (ESObjectUtils.isNull(array)) {
            return null;
        }
        return new LogicalExpression(array, LogicalOperator.OR);
    }
}
