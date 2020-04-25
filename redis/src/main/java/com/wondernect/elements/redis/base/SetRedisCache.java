package com.wondernect.elements.redis.base;

import com.wondernect.elements.common.utils.ESObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.SetOperations;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: SetRedisCache
 * Author: chenxun
 * Date: 2018/10/30 15:07
 * Description: 先进先出,尾部插入,不允许重复value
 */
public abstract class SetRedisCache<T> extends BaseRedisCache {

    private static final Logger logger = LoggerFactory.getLogger(SetRedisCache.class);

    @Autowired
    private SetOperations<String, Object> setOperations;

    /**
     * 添加一个或多个指定的member元素到集合的 key中.
     * 指定的一个或者多个元素member,如果已经在集合key中存在则忽略.
     * 如果集合key不存在，则新建集合key,并添加member元素到集合key中.
     * 如果集合key存在,但是key的类型不是集合则返回错误.
     * 结果返回新成功添加到集合里元素的数量，不包括已经存在于集合中的元素.
     */
    public Long add(String key, T object) {
        if (ESObjectUtils.isNull(object)) {
            logger.warn("redis set warn, add object is null !!!");
        }
        return setOperations.add(generateRedisServiceKey(key), object);
    }
}
