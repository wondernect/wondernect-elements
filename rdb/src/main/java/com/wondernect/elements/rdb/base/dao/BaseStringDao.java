package com.wondernect.elements.rdb.base.dao;

import com.wondernect.elements.algorithm.snowflake.SnowFlakeAlgorithm;
import com.wondernect.elements.rdb.base.model.BaseStringModel;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: BaseStringDao
 * Author: chenxun
 * Date: 2019/3/14 18:25
 * Description:
 */
public abstract class BaseStringDao<T extends BaseStringModel> extends BaseIDDao<T, String> {

    @Autowired
    private SnowFlakeAlgorithm snowFlakeAlgorithm;

    @Override
    public String generateIdentifier() {
        return String.valueOf(snowFlakeAlgorithm.getSnowflake().nextId());
    }
}
