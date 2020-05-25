package com.wondernect.elements.generator;

import com.wondernect.elements.common.utils.ESUUIDGenerateUtils;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: TokenGenerator
 * Author: chenxun
 * Date: 2018/5/31 下午7:02
 * Description: uuid generator
 */
public class UUIDIdentifierGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        return ESUUIDGenerateUtils.generateIdentifier("-");
    }
}
