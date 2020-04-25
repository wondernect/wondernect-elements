package com.wondernect.elements.redis.base;

import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.common.utils.ESStringUtils;
import com.wondernect.elements.redis.config.RedisConfigProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: BaseRedisCache
 * Author: chenxun
 * Date: 2019/3/15 15:16
 * Description:
 */
public abstract class BaseRedisCache {

    private static final Logger logger = LoggerFactory.getLogger(BaseRedisCache.class);

    @Autowired
    private RedisConfigProperties redisConfigProperties;

    public String generateRedisServiceKey(String key) {
        if (ESStringUtils.isRealEmpty(redisConfigProperties.getServiceIdentifier())) {
            return key;
        }
        return redisConfigProperties.getServiceIdentifier() + redisConfigProperties.getKeySeparator() + key;
    }

    public Long generateExpires(Long expires) {
        if (ESObjectUtils.isNull(expires) || expires <= redisConfigProperties.getMinExpires()) {
            logger.warn("redis expires {} is null or less than {}, we will use default expires {} !!!", expires, redisConfigProperties.getMinExpires(), redisConfigProperties.getDefaultExpires());
            expires = redisConfigProperties.getDefaultExpires();
        }
        return expires;
    }
}
