package com.wondernect.elements.mail.client;

import com.wondernect.elements.mail.client.util.MailSendResult;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Map;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: MailClient
 * Author: chenxun
 * Date: 2018/11/9 13:27
 * Description:
 */
public interface MailClient {

    /**
     * plain文本
     */
    MailSendResult sendPlainTextMail(JavaMailSenderImpl javaMailSender,String personal, String toAddress, String subject, String plainText, Map<String, Object> varibles);

    /**
     * html文本
     */
    MailSendResult sendHtmlMail(JavaMailSenderImpl javaMailSender,String personal, String toAddress, String subject, String htmlContent);
}
