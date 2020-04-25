package com.wondernect.elements.http.client.util;

import okhttp3.Request;
import org.apache.commons.collections4.MapUtils;

import java.util.Iterator;
import java.util.Map;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: EHttpClientUtil
 * Author: chenxun
 * Date: 2018/7/31 下午3:00
 * Description: http client util
 */
public final class HttpClientUtil {

    public static Request.Builder generatorBuilder(Map<String, String> headers) {
        Request.Builder builder = new Request.Builder();
        if (MapUtils.isNotEmpty(headers)) {
            Iterator iterator = headers.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = (Map.Entry<String, String>) iterator.next();
                builder.header(entry.getKey(), entry.getValue());
            }
        }
        return builder;
    }

    public static String generatorUrl(String url, Map<String, String> params) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (MapUtils.isNotEmpty(params)) {
            stringBuilder.append("?");
            Iterator iterator = params.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = (Map.Entry<String, String>) iterator.next();
                stringBuilder.append("&").append(entry.getKey()).append("=").append(entry.getValue());
            }
        }
        return url + stringBuilder.toString();
    }
}
