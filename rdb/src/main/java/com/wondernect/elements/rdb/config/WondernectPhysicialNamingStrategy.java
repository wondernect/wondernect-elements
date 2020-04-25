package com.wondernect.elements.rdb.config;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Locale;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: DefaultNamingStrategy
 * Author: chenxun
 * Date: 2018/10/19 11:26
 * Description:
 */
@Component
public class WondernectPhysicialNamingStrategy extends SpringPhysicalNamingStrategy {

    @Autowired
    private RDBConfigProperties rdbConfigProperties;

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        return this.apply(name, jdbcEnvironment);
    }

    private Identifier apply(Identifier name, JdbcEnvironment jdbcEnvironment) {
        if (name == null) {
            return null;
        } else {
            StringBuilder builder = new StringBuilder(name.getText().replace('.', '_'));

            for(int i = 1; i < builder.length() - 1; ++i) {
                if (this.isUnderscoreRequired(builder.charAt(i - 1), builder.charAt(i), builder.charAt(i + 1))) {
                    builder.insert(i++, '_');
                }
            }

            return this.getIdentifierExtend(builder.toString(), name.isQuoted(), jdbcEnvironment);
        }
    }

    private Identifier getIdentifierExtend(String name, boolean quoted, JdbcEnvironment jdbcEnvironment) {
        if (this.isCaseInsensitiveExtend(jdbcEnvironment)) {
            name = name.toLowerCase(Locale.ROOT);
        }
        if (CollectionUtils.isEmpty(rdbConfigProperties.getTablePrefixBlackList()) || !rdbConfigProperties.getTablePrefixBlackList().contains(name)) {
            name = rdbConfigProperties.getTablePrefix() + name;
        }
        return new Identifier(name, quoted);
    }

    private boolean isCaseInsensitiveExtend(JdbcEnvironment jdbcEnvironment) {
        return true;
    }

    private boolean isUnderscoreRequired(char before, char current, char after) {
        return Character.isLowerCase(before) && Character.isUpperCase(current) && Character.isLowerCase(after);
    }
}
