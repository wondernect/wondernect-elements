package com.wondernect.elements.sms.client;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.wondernect.elements.common.utils.ESStringUtils;
import com.wondernect.elements.sms.client.config.AliyunSMSConfigProperties;
import com.wondernect.elements.sms.client.util.SMSSendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: SMSClientImpl
 * Author: chenxun
 * Date: 2018/11/12 14:41
 * Description:
 */
@Service
public class AliyunSMSClient {

    private static final String product = "Dysmsapi"; //短信API产品名称（短信产品名固定，无需修改）

    private static final String domain = "dysmsapi.aliyuncs.com"; //短信API产品域名（接口地址固定，无需修改）

    @Autowired
    private AliyunSMSConfigProperties aliyunSMSConfigProperties;

    public SMSSendResult send(String phoneNumber, String templateCode, String templateParam, String signName) {
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", aliyunSMSConfigProperties.getAccessKeyId(), aliyunSMSConfigProperties.getAccessKeySecret());
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        } catch (ClientException e) {
            return new SMSSendResult(phoneNumber, templateParam, false, e.getLocalizedMessage());
        }
        IAcsClient acsClient = new DefaultAcsClient(profile);
        SendSmsRequest request = new SendSmsRequest();
        request.setMethod(MethodType.POST);
        request.setPhoneNumbers(phoneNumber);
        request.setTemplateCode(templateCode);
        request.setTemplateParam(templateParam);
        request.setSignName(signName);
        SendSmsResponse sendSmsResponse;
        try {
            sendSmsResponse = acsClient.getAcsResponse(request);
        } catch (ClientException e) {
            return new SMSSendResult(phoneNumber, templateParam, false, e.getLocalizedMessage());
        }
        if(ESStringUtils.isRealEmpty(sendSmsResponse.getCode()) || !sendSmsResponse.getCode().equalsIgnoreCase("OK")) {
            return new SMSSendResult(phoneNumber, templateParam, false, sendSmsResponse.getMessage());
        }
        return new SMSSendResult(phoneNumber, templateParam, true, "SMS send SUCCESS");
    }
}
