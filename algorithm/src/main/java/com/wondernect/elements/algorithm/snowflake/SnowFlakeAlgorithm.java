package com.wondernect.elements.algorithm.snowflake;

import com.wondernect.elements.algorithm.snowflake.config.SnowFlakeConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: AlgorithmService
 * Author: chenxun
 * Date: 2018/5/30 15:30
 * Description: algorithm
 */
@Service
public class SnowFlakeAlgorithm {

    @Autowired
    private SnowFlakeConfigProperties snowFlakeConfigProperties;

    private SnowFlake initSnowflake() {
        return SnowFlakeHolder.init(
                snowFlakeConfigProperties.getStartTimestamp(),
                snowFlakeConfigProperties.getWorkerId(),
                snowFlakeConfigProperties.getDataId(),
                snowFlakeConfigProperties.isTimeCheck()
        );
    }

    public SnowFlake getSnowflake() {
        SnowFlake snowFlake = SnowFlakeHolder.get();
        if (null == snowFlake) {
            snowFlake = initSnowflake();
        }
        return snowFlake;
    }
}
