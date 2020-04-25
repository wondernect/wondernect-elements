package com.wondernect.elements.redis.base;

import com.wondernect.elements.common.utils.ESObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;

import java.util.List;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: ListRedisCache
 * Author: chenxun
 * Date: 2018/10/30 15:07
 * Description: 先进后出,头部插入,允许重复value
 */
public abstract class ListRedisCache<T> extends BaseRedisCache {

    private static final Logger logger = LoggerFactory.getLogger(ListRedisCache.class);

    @Autowired
    private ListOperations<String, Object> listOperations;

    /**
     * 返回存储在 key 的列表里指定范围内的元素
     * list的第一个元素下标是0（list的表头），第二个元素下标是1，-1 表示列表的最后一个元素，-2 是倒数第二个，以此类推。
     * LRANGE list 0 10 这个命令会返回11个元素，即最右边的那个元素也会被包含在内。
     * 当下标超过list范围的时候不会产生error。
     * 如果start比list的尾部下标大的时候，会返回一个空列表。
     * 如果stop比list的实际尾部大的时候，Redis会当它是最后一个元素的下标。
     */
    public List<T> range(String key, long start, long end) {
        return (List<T>) listOperations.range(generateRedisServiceKey(key), start, end);
    }

    /**
     * 将所有指定的值插入到存于key的列表的头部。
     * 如果key不存在，那么在进行push操作前会创建一个空列表。
     * 如果key对应的值不是一个list的话，那么会返回一个错误。
     * 可以使用一个命令把多个元素push进入列表，只需在命令末尾加上多个指定的参数。
     * 元素是从最左端的到最右端的、一个接一个被插入到list的头部。所以对于这个命令例子 LPUSH mylist a b c，返回的列表是c为第一个元素，b为第二个元素，a为第三个元素。
     * 结果返回push 操作后的list长度.
     */
    public Long leftPush(String key, T object) {
        if (ESObjectUtils.isNull(object)) {
            logger.warn("redis list warn, left push object is null !!!");
        }
        return listOperations.leftPush(generateRedisServiceKey(key), object);
    }
}
