package com.wondernect.elements.i18n;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: I18nMessageService
 * Author: chenxun
 * Date: 2018/12/3 15:55
 * Description:
 */
@Service
public class I18nMessageService {

    @Autowired
    private MessageSource messageSource;

    /**
     * 获取指定code国际化消息
     * @param code 消息表示
     * @param defaultMessage 默认消息
     * @param messageArguments {0} {1} ... 参数值
     * @return 国际化消息
     */
    public String getMessage(String code, String defaultMessage, Object... messageArguments) {
        return messageSource.getMessage(code, messageArguments, defaultMessage, LocaleContextHolder.getLocale());
    }
}
