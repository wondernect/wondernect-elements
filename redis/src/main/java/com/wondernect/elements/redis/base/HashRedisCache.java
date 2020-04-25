package com.wondernect.elements.redis.base;

import com.wondernect.elements.common.utils.ESObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;

import java.util.Set;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: HashRedisCacheImpl
 * Author: chenxun
 * Date: 2018/10/30 11:05
 * Description:
 */
public abstract class HashRedisCache<T> extends BaseRedisCache {

    private static final Logger logger = LoggerFactory.getLogger(HashRedisCache.class);

    @Autowired
    private HashOperations<String, String, Object> hashOperations;

    public void put(String hash, String hashKey, T hashValue) {
        if (ESObjectUtils.isNull(hashValue)) {
            logger.warn("redis hash warn, put hash value is null !!!");
        }
        hashOperations.put(generateRedisServiceKey(hash), hashKey, hashValue);
    }

    public void delete(String hash, String hashKey) {
        boolean hasKey = hashOperations.hasKey(generateRedisServiceKey(hash), hashKey);
        if (hasKey) {
            hashOperations.delete(generateRedisServiceKey(hash), hashKey);
        }
    }

    public T get(String hash, String hashKey) {
        Object o = hashOperations.get(generateRedisServiceKey(hash), hashKey);
        if (ESObjectUtils.isNotNull(o)) {
            return (T) o;
        }
        return null;
    }

    public Set<String> keys(String hash) {
        return hashOperations.keys(generateRedisServiceKey(hash));
    }

    public Long size(String hash) {
        return hashOperations.size(generateRedisServiceKey(hash));
    }
}
