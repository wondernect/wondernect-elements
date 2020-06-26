package com.wondernect.elements.rdb.base.dao;

import com.wondernect.elements.algorithm.snowflake.SnowFlakeAlgorithm;
import com.wondernect.elements.rdb.base.model.BaseLongModel;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: BaseLongDao
 * Author: chenxun
 * Date: 2019/3/15 9:56
 * Description:
 */
public abstract class BaseLongDao<T extends BaseLongModel> extends BaseIDDao<T, Long> {

    @Autowired
    private SnowFlakeAlgorithm snowFlakeAlgorithm;

    @Override
    public Long generateIdentifier() {
        return snowFlakeAlgorithm.getSnowflake().nextId();
    }
}
