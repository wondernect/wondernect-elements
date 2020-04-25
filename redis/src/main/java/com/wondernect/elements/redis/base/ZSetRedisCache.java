package com.wondernect.elements.redis.base;

import com.wondernect.elements.common.utils.ESStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.Set;

/**
 * Created on 2017/10/21.
 * wondernect.com
 * @author sunbeam
 */
public abstract class ZSetRedisCache extends BaseRedisCache {

    private static final Logger logger = LoggerFactory.getLogger(ZSetRedisCache.class);

    @Autowired
    private ZSetOperations<String, Object> zSetOperations;

    /**
     * 用于将一个或多个成员元素及其分数值加入到有序集当中
     * 如果某个成员已经是有序集的成员，那么更新这个成员的分数值，
     * 并通过重新插入这个成员元素，来保证该成员在正确的位置上
     */
    public void add(String key, String member, double score) {
        if (ESStringUtils.isRealEmpty(member)) {
            logger.warn("redis zset warn, add member is null !!!");
        }
        zSetOperations.add(generateRedisServiceKey(key), member, score);
    }

    /**
     * 返回有序集中，成员的分数值
     * 如果成员元素不是有序集 key 的成员，或 key 不存在，返回 nil
     */
    public Double score(String key, String member) {
        return zSetOperations.score(generateRedisServiceKey(key), member);
    }

    /**
     * 计算集合中元素的数量
     * 当 key 存在且是有序集类型时，返回有序集的基数
     * 当 key 不存在时，返回 0
     */
    public Long zCard(String key) {
        return zSetOperations.zCard(generateRedisServiceKey(key));
    }

    /**
     * 用于移除有序集中的一个或多个成员，不存在的成员将被忽略
     * 返回值 - 被成功移除的成员的数量，不包括被忽略的成员
     */
    public Long remove(String key, String member) {
        return zSetOperations.remove(generateRedisServiceKey(key), member);
    }

    /**
     * 对有序集合中指定成员的分数加上增量 increment
     * 通过传递一个负数值 increment ，让分数减去相应的值
     * 当 key 不存在，或 member 不是 key 的成员时，
     * ZINCRBY key increment member 等同于 ZADD key increment member 。
     * 返回值 - member 成员的新分数值，以字符串形式表示
     */
    public Double incrementScore(String key, String member, double increment) {
        return zSetOperations.incrementScore(generateRedisServiceKey(key), member, increment);
    }

    /**
     * 返回有序集中，指定区间内的成员
     * 成员的位置按分数值递增(从小到大)来排序
     * 具有相同分数值的成员按字典序(lexicographical order )来排列
     * 下标参数 start 和 stop 以 0 表示有序集第一个成员，以 -1 表示最后一个成员
     */
    public Set<ZSetOperations.TypedTuple<Object>> rangeWithScores(String key, long offset, long count) {
        return zSetOperations.rangeWithScores(generateRedisServiceKey(key), offset, offset + count);
    }

    /**
     * 返回有序集中，指定区间内的成员
     * 成员的位置按分数值递减(从大到小)来排列
     * 具有相同分数值的成员按字典序的逆序(reverse lexicographical order)排列
     * 下标参数 start 和 stop 以 0 表示有序集第一个成员，以 -1 表示最后一个成员
     */
    public Set<ZSetOperations.TypedTuple<Object>> reverseRangeWithScores(String key, long offset, long count) {
        return zSetOperations.reverseRangeWithScores(generateRedisServiceKey(key), offset, offset + count);
    }

    /**
     * 返回有序集中指定成员的排名
     * 其中有序集成员按分数值递增(从小到大)顺序排列
     */
    public Long rank(String key, String member) {
        return zSetOperations.rank(generateRedisServiceKey(key), member);
    }

    /**
     * 返回有序集中成员的排名
     * 其中有序集成员按分数值递减(从大到小)排序
     */
    public Long revrank(String key, String member) {
        return zSetOperations.reverseRank(generateRedisServiceKey(key), member);
    }
}
