package com.wondernect.elements.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: ESCMDUtils
 * Author: chenxun
 * Date: 2019/3/22 16:16
 * Description: cmd utils
 */
public final class ESCMDUtils {

    private static final Logger logger = LoggerFactory.getLogger(ESCMDUtils.class);

    public static String execCMDWithAllResult(String cmd) {
        StringBuilder result = new StringBuilder();
        Runtime runtime = Runtime.getRuntime();
        try {
            InputStream inputStream = runtime.exec(cmd).getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result.append(line + "\n");
            }
        } catch (IOException e) {
            logger.error(e.getLocalizedMessage(), e);
        }
        return result.toString();
    }

    public static String execCMDWithLastLine(String cmd) {
        String result = "";
        Runtime runtime = Runtime.getRuntime();
        try {
            InputStream inputStream = runtime.exec(cmd).getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result = line;
            }
        } catch (IOException e) {
            logger.error(e.getLocalizedMessage(), e);
        }
        return result;
    }
}
