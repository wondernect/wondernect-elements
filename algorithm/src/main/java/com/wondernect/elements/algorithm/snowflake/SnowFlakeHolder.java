package com.wondernect.elements.algorithm.snowflake;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: SnowFlakeHolder
 * Author: chenxun
 * Date: 2018/5/30 下午2:20
 * Description: snowflake algorithm service
 */
final class SnowFlakeHolder {

    /** * The SnowFlake instance. */
    private static final ThreadLocal<SnowFlake> SNOWFLAKE = new InheritableThreadLocal<>();

    static SnowFlake init(Long startTimestamp, Long workerId, Long datacenterId, boolean timeCheck) {
        SnowFlake snowFlake = SNOWFLAKE.get();
        if (null == snowFlake) {
            snowFlake = new SnowFlake(startTimestamp, workerId, datacenterId, timeCheck);
            SNOWFLAKE.set(snowFlake);
        }
        return snowFlake;
    }

    static void destroy() {
        SNOWFLAKE.remove();
    }

    static SnowFlake get() {
        return SNOWFLAKE.get();
    }
}
