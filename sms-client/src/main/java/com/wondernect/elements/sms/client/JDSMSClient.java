package com.wondernect.elements.sms.client;

import com.wondernect.elements.common.utils.ESJSONObjectUtils;
import com.wondernect.elements.common.utils.ESStringUtils;
import com.wondernect.elements.http.client.HttpClient;
import com.wondernect.elements.sms.client.config.JDSMSConfigProperties;
import com.wondernect.elements.sms.client.util.SMSSendResult;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: JDSMSClient
 * Author: chenxun
 * Date: 2019/9/12 12:14
 * Description: 京东短信发送客户端
 */
@Service
public class JDSMSClient {

    @Autowired
    private HttpClient httpClient;

    @Autowired
    private JDSMSConfigProperties jdsmsConfigProperties;

    public SMSSendResult send(String phoneNumber, String content) {
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("action", jdsmsConfigProperties.getAction());
        urlParams.put("account", jdsmsConfigProperties.getAccount());
        urlParams.put("password", jdsmsConfigProperties.getPassword());
        urlParams.put("mobile", phoneNumber);
        urlParams.put("content", content);
        urlParams.put("extno", jdsmsConfigProperties.getExtno());
        urlParams.put("rt", jdsmsConfigProperties.getRt());
        Boolean result = true;
        String message = null;
        String response = httpClient.getForJson(jdsmsConfigProperties.getUrl(), null, urlParams);
        if (ESStringUtils.isBlank(response)) {
            result = false;
            message = "短信请求失败,响应数据为空";
        } else {
            Map<String, Object> json = ESJSONObjectUtils.jsonStringToMapClassObject(response, Object.class);
            if (MapUtils.isEmpty(json)) {
                result = false;
                message = "短信请求失败,响应数据解析为空:" + response;
            } else {
                String status = json.get("status").toString();
                if (ESStringUtils.isBlank(status)) {
                    result = false;
                    message = "短信请求失败,响应数据解析后获取请求结果为空:" + response;
                } else {
                    if (!ESStringUtils.equalsIgnoreCase(status, "0")) {
                        result = false;
                        message = "短信请求失败,响应数据解析后获取请求结果为:" + status;
                    }
                }
            }
        }
        return new SMSSendResult(phoneNumber, content, result, message);
    }
}
