package com.wondernect.elements.algorithm.snowflake.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: SnowFlakeConfigProperties
 * Author: chenxun
 * Date: 2018/5/25 10:37
 * Description: SnowFlake配置
 */
@Component
@Primary
@PropertySource(value = {"classpath:application.properties", "classpath:application.yml", "classpath:application.yaml"}, ignoreResourceNotFound = true)
@ConfigurationProperties(prefix = "wondernect.elements.algorithm.snowflake")
public class SnowFlakeConfigProperties implements Serializable {

    private static final long serialVersionUID = 2063732733149304087L;

    private Long startTimestamp = 1514736000000L; // 开始时间截 (2018-01-01) 毫秒级时间戳

    private Long workerId = 0L; // 机器号 范围 0-7

    private Long dataId = 0L; // 数据源 用于区分机器上不同应用  范围 0-7

    private boolean timeCheck = true; // 是否检查服务器时间戳回退

    public Long getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(Long startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }

    public Long getDataId() {
        return dataId;
    }

    public void setDataId(Long dataId) {
        this.dataId = dataId;
    }

    public boolean isTimeCheck() {
        return timeCheck;
    }

    public void setTimeCheck(boolean timeCheck) {
        this.timeCheck = timeCheck;
    }
}

