package com.wondernect.elements.mail.client.impl;

import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.mail.client.MailClient;
import com.wondernect.elements.mail.client.config.MailClientConfigProperties;
import com.wondernect.elements.mail.client.util.MailSendResult;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: DefaultMailClient
 * Author: chenxun
 * Date: 2018/11/12 9:51
 * Description:
 */
@Service(value = "mail_client")
public class DefaultMailClient implements MailClient {

    private static final Logger logger = LoggerFactory.getLogger(DefaultMailClient.class);

    @Override
    public MailSendResult sendPlainTextMail(String host, int port, String username, String password, String personal, String toAddress, String subject, String plainText, Map<String, Object> varibles) {
        if (MapUtils.isNotEmpty(varibles)) {
            for (String key : varibles.keySet()) {
                if (ESObjectUtils.isNull(varibles.get(key))) {
                    continue;
                }
                plainText = plainText.replace(key, varibles.get(key).toString());
            }
        }
        return sendMail(host, port, username, password, username, personal, toAddress, subject, plainText, false);
    }

    @Override
    public MailSendResult sendHtmlMail(String host, int port, String username, String password, String personal, String toAddress, String subject, String htmlContent) {
        return sendMail(host, port, username, password, username, personal, toAddress, subject, htmlContent, true);
    }

    private MailSendResult sendMail(String host, int port, String username, String password,
                                    String fromAddress, String fromName, String toAddress, String subject, String content, boolean isHtml) {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(host);
        javaMailSender.setPort(port);
        javaMailSender.setUsername(username);
        javaMailSender.setPassword(password);
        MailSendResult mailSendResult = null;
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());
            mimeMessageHelper.setFrom(fromAddress);
            mimeMessageHelper.setTo(toAddress);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(content, isHtml);
        } catch (MessagingException e) {
            logger.error("MailClient send mail failed, message={}, stacktrace={}", e.getLocalizedMessage(), e.getStackTrace());
            mailSendResult = new MailSendResult(fromAddress, fromName, toAddress, subject, content, false, e.getLocalizedMessage());
        }
        if (ESObjectUtils.isNotNull(mailSendResult)) {
            return mailSendResult;
        }
        try {
            javaMailSender.send(mimeMessage);
        } catch (MailException e) {
            logger.error("MailClient send mail failed, message={}, stacktrace={}", e.getLocalizedMessage(), e.getStackTrace());
            mailSendResult = new MailSendResult(fromAddress, fromName, toAddress, subject, content, false, e.getLocalizedMessage());
        }
        if (ESObjectUtils.isNull(mailSendResult)) {
            mailSendResult = new MailSendResult(fromAddress, fromName, toAddress, subject, content, true, "Mail send SUCCESS");
        }
        return mailSendResult;
    }
}
