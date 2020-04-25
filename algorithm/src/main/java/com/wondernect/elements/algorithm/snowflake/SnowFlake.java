package com.wondernect.elements.algorithm.snowflake;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: SnowFlake
 * Author: chenxun
 * Date: 2018/5/25 10:33
 * Description: snowflake算法
 */
public class SnowFlake {

    /** 机器id所占的位数 */
    private static final long workerIdBits = 3L;

    /** 数据标识id所占的位数 */
    private static final long datacenterIdBits = 3L;

    /**
     * 支持的最大机器id，结果是7 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数)
     * -1L ^ (-1L << n)表示占n个bit的数字的最大值是多少。举个栗子：-1L ^ (-1L << 2)等于10进制的3 ，即二进制的11表示十进制3。
     */
    private static final long maxWorkerId = -1L ^ (-1L << workerIdBits);

    /**
     * 支持的最大数据标识id，结果是7
     *  -1L 原码 1000 0001   原码是在符号位加标志  正数 0 负数1
     *     反码1111 1110   正数原码是其本身 负数是符号位不变其余位取反
     *     补码1111 1111   原码-1再取反
     *  -1L << 3 1111 1000
     *  -1L ^ (1111 1000)   1111 1000 ^ 1111 1111 结果为7
     * */
    private static final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);

    /** 序列在id中占的位数 */
    private static final long sequenceBits = 14L;

    /** 机器ID向左移14位 */
    private static final long workerIdShift = sequenceBits;

    /** 数据标识id向左移17位(14+3) */
    private static final long datacenterIdShift = sequenceBits + workerIdBits;

    /** 时间截向左移20位(3+3+14) 机器码+计数器 */
    private static final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

    /** 生成序列的掩码，这里为16383  */
    private static final long sequenceMask = -1L ^ (-1L << sequenceBits);

    /** 毫秒内序列(0~16383) */
    private long sequence = 0L;

    /** 上次生成ID的时间截 */
    private long lastTimestamp = -1L;

    /** 开始时间截 (2018-01-01) 毫秒级时间戳 */
    private long startTimestamp;

    /** 工作机器ID(0~7) */
    private long workerId;

    /** 数据中心ID(0~7) */
    private long datacenterId;

    /** 是否检查时间回退错误 */
    private boolean timeCheck = true;

    /**
     * 构造函数
     */
    public SnowFlake (long startTimestamp, long workerId, long datacenterId, boolean timeCheck) {
        long timestamp = System.currentTimeMillis();
        if (startTimestamp == 0 || startTimestamp > timestamp) {
            throw new IllegalArgumentException(String.format("start timestamp can't be zero or greater than current time %d", timestamp));
        }
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        this.startTimestamp = startTimestamp;
        this.workerId = workerId;
        this.datacenterId = datacenterId;
        this.timeCheck = timeCheck;
    }

    /**
     * 获得下一个ID (该方法是线程安全的)
     * @return SnowflakeId
     */
    public synchronized long nextId() {
        long timestamp = System.currentTimeMillis();

        //如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timeCheck && (timestamp < lastTimestamp)) {
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        //如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            //序列号 = 上次序列号+1 与 生成序列的掩码
            sequence = (sequence + 1) & sequenceMask;
            //毫秒内序列溢出
            if (sequence == 0) {
                //阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            //时间戳改变，毫秒内序列重置
            sequence = 0L;
        }

        //上次生成ID的时间截（保存时间戳）
        lastTimestamp = timestamp;

        //移位并通过或运算拼到一起组成64位的ID(或运算)
        return ((timestamp - startTimestamp) << timestampLeftShift) //当前时间戳-开始时间戳 左移22位相当于两个差的22位
                | (datacenterId << datacenterIdShift) //数据中心Id左移17位
                | (workerId << workerIdShift) // 机器id左移12位
                | sequence; //毫秒内序列
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }
}
