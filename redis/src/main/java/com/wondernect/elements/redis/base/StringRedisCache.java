package com.wondernect.elements.redis.base;

import com.wondernect.elements.common.utils.ESObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: StringRedisCache
 * Author: chenxun
 * Date: 2018/10/30 14:44
 * Description:
 */
public abstract class StringRedisCache<T> extends BaseRedisCache {

    private static final Logger logger = LoggerFactory.getLogger(StringRedisCache.class);

    @Autowired
    private ValueOperations<String, Object> valueOperations;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void set(String key, T object) {
        valueOperations.set(generateRedisServiceKey(key), object);
    }

    public void set(String key, T object, Long expires, TimeUnit timeUnit) {
        if (ESObjectUtils.isNull(object)) {
            logger.warn("redis string warn, set object is null !!!");
        }
        valueOperations.set(generateRedisServiceKey(key), object, generateExpires(expires), timeUnit);
    }

    public void delete(String key) {
        boolean hasKey = redisTemplate.hasKey(generateRedisServiceKey(key));
        if (hasKey) {
            redisTemplate.delete(generateRedisServiceKey(key));
        }
    }

    public T get(String key) {
        Object o = valueOperations.get(generateRedisServiceKey(key));
        if (ESObjectUtils.isNotNull(o)) {
            return (T) o;
        }
        return null;
    }
}
