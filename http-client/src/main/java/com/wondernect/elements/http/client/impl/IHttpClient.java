package com.wondernect.elements.http.client.impl;

import com.wondernect.elements.common.utils.ESJSONObjectUtils;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.http.client.HttpClient;
import com.wondernect.elements.http.client.util.HttpClientUtil;
import okhttp3.*;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: EHttpClient
 * Author: chenxun
 * Date: 2018/7/31 下午2:10
 * Description: http client
 */
@Service(value = "http_client")
public class IHttpClient implements HttpClient {

    private static final Logger logger = LoggerFactory.getLogger(IHttpClient.class);

    @Autowired
    private OkHttpClient okHttpClient;

    public String getForJson(String url, Map<String, String> headers, Map<String, String> urlParams) {
        Request.Builder builder = HttpClientUtil.generatorBuilder(headers);
        Request request = builder.get().url(HttpClientUtil.generatorUrl(url, urlParams)).build();
        return executeRequest(request);
    }

    public String postForJson(String url, Map<String, String> headers, Map<String, String> urlParams, Map<String, String> bodyParams) {
        Request.Builder builder = HttpClientUtil.generatorBuilder(headers);
        RequestBody requestBody = null;
        if (MapUtils.isNotEmpty(bodyParams)) {
            requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), ESJSONObjectUtils.jsonObjectToJsonString(bodyParams));
        }
        Request request = builder.post(requestBody).url(HttpClientUtil.generatorUrl(url, urlParams)).build();
        return executeRequest(request);
    }

    public String putForJson(String url, Map<String, String> headers, Map<String, String> urlParams, Map<String, String> bodyParams) {
        Request.Builder builder = HttpClientUtil.generatorBuilder(headers);
        RequestBody requestBody = null;
        if (MapUtils.isNotEmpty(bodyParams)) {
            requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), ESJSONObjectUtils.jsonObjectToJsonString(bodyParams));
        }
        Request request = builder.put(requestBody).url(HttpClientUtil.generatorUrl(url, urlParams)).build();
        return executeRequest(request);
    }

    public String deleteForJson(String url, Map<String, String> headers, Map<String, String> urlParams, Map<String, String> bodyParams) {
        Request.Builder builder = HttpClientUtil.generatorBuilder(headers);
        RequestBody requestBody = null;
        if (MapUtils.isNotEmpty(bodyParams)) {
            requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), ESJSONObjectUtils.jsonObjectToJsonString(bodyParams));
        }
        Request request = builder.delete(requestBody).url(HttpClientUtil.generatorUrl(url, urlParams)).build();
        return executeRequest(request);
    }

    private String executeRequest(Request request) {
        String responseBody = "";
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            if (ESObjectUtils.isNotNull(response) && response.isSuccessful()) {
                responseBody = response.body().string();
            } else {
                logger.error("HttpClient响应非成功状态:{}", response);
            }
        } catch (IOException e) {
            logger.error("HttpClient执行异常", e);
        } finally {
            if (ESObjectUtils.isNotNull(response)) {
                response.close();
            }
        }
        return responseBody;
    }
}
