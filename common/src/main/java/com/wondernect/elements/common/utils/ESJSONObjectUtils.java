package com.wondernect.elements.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * wondernect.com
 * Created by cxhome on 2017/8/26.
 *
 * @author sunbeam
 */
public final class ESJSONObjectUtils {

    private static final Logger logger = LoggerFactory.getLogger(ESJSONObjectUtils.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * json object -> json string
     */
    public static String jsonObjectToJsonString(Object value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            logger.error("json object to json string failed", e);
        }
        return null;
    }

    /**
     * json string -> custom class object
     */
    public static <T> T jsonStringToClassObject(String jsonString, Class<T> valueType) {
        try {
            return objectMapper.readValue(jsonString, valueType);
        } catch (IOException e) {
            logger.error("json string to custom class object failed", e);
        }
        return null;
    }

    /**
     * json string -> custom list<Object> class object
     */
    public static <T> List<T> jsonStringToListClassObject(String jsonString, Class<T> valueType) {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, valueType);
        try {
            return objectMapper.readValue(jsonString, javaType);
        } catch (IOException e) {
            logger.error("json string to custom list class object failed", e);
        }
        return null;
    }

    /**
     * json string -> custom map<String, Object></> class object
     */
    public static <T> Map<String, T> jsonStringToMapClassObject(String jsonString, Class<T> valueType) {
        if (ESStringUtils.isBlank(jsonString)) {
            return null;
        }
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(Map.class, String.class, valueType);
        try {
            return objectMapper.readValue(jsonString, javaType);
        } catch (IOException e) {
            logger.error("json string to custom map class object failed", e);
        }
        return null;
    }

    /**
     * 通过网络访问json并读取文件
     *
     * @param url:http://127.0.0.1:80/dashboard/dept_uuid.json
     */
    public static String loadJsonStringFromUrl(String url) {
        StringBuilder json = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(ESHttpUtils.getUrlConnectionInputStream(url), StandardCharsets.UTF_8));
            String inputLine;
            while ((inputLine = bufferedReader.readLine()) != null) {
                json.append(inputLine);
            }
            bufferedReader.close();
        } catch (Exception e) {
            logger.error("load json string from url failed", e);
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException el) {
                    logger.error("load json string from url failed", el);
                }
            }
        }
        return json.toString();
    }

    /**
     * 通过本地文件访问json并读取
     *
     * @param path：E:/svn/05.Hospital/templatedept_uuid.json
     */
    public static String loadJsonStringFromLocalPath(String path) {
        StringBuilder json = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(ESFileUtils.getFileInputStream(path), StandardCharsets.UTF_8));
            String inputLine;
            while ((inputLine = bufferedReader.readLine()) != null) {
                json.append(inputLine);
            }
            bufferedReader.close();
        } catch (IOException e) {
            logger.error("load json string from local path failed", e);
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException el) {
                    logger.error("load json string from local path failed", el);
                }
            }
        }
        return json.toString();
    }

    public static void main(String[] args) {
        // Map<String, Double> map = new HashMap<>();
        // map.put("5.0", 10.0);
        // map.put("10.0", 20.0);
        // map.put("30.0", 50.0);
        // String json = jsonObjectToJsonString(map);
        // System.out.println(json);
        // Map<String, Double> jsonMap = jsonStringToMapClassObject(json, Double.class);
        // System.out.println(jsonMap);
        // System.out.println(jsonMap.keySet());
        // System.out.println(jsonMap.values());
        // System.out.println(loadJsonStringFromLocalPath("F:\\aa.json"));
        System.out.println(loadJsonStringFromUrl("https://service.cela.gov.cn/system/studyinfo/file/url?signature=eyJzdWIiOiJPUEVOIiwiaXNzIjoiOGE5OThhMTU3MjcyODYwYTAxNzI3MmJlMzJmMjAwMjciLCJleHAiOjE1OTc4MTA4MzB9.bHjASnTEdIlhLSM-dCEl4h6QdtytIYVdQXVtIR4LPP4&token=3daa530bd9191b1c782148dc3de67e64&file_id=8a998a1474024c9801740250155f002b"));
    }
}
