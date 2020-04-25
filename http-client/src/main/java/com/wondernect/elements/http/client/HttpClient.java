package com.wondernect.elements.http.client;

import java.util.Map;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: EHttpClient
 * Author: chenxun
 * Date: 2018/11/13 9:32
 * Description:
 */
public interface HttpClient {

    /**
     * get请求
     */
    String getForJson(String url, Map<String, String> headers, Map<String, String> urlParams);

    /**
     * post请求
     */
    String postForJson(String url, Map<String, String> headers, Map<String, String> urlParams, Map<String, String> bodyParams);

    /**
     * put请求
     */
    String putForJson(String url, Map<String, String> headers, Map<String, String> urlParams, Map<String, String> bodyParams);

    /**
     * delete请求
     */
    String deleteForJson(String url, Map<String, String> headers, Map<String, String> urlParams, Map<String, String> bodyParams);
}
