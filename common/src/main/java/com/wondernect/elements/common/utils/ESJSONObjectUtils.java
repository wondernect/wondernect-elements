package com.wondernect.elements.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
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

    private static class miTM implements TrustManager,X509TrustManager {
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public boolean isServerTrusted(X509Certificate[] certs) {
            return true;
        }

        public boolean isClientTrusted(X509Certificate[] certs) {
            return true;
        }

        public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {

        }

        public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException {

        }
    }

    private static void trustAllHttpsCertificates() throws Exception {
        TrustManager[] trustAllCerts = new TrustManager[1];
        TrustManager tm = new miTM();
        trustAllCerts[0] = tm;
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, null);
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    }

    /**
     * 忽略HTTPS请求的SSL证书，必须在openConnection之前调用
     */
    private static void ignoreSsl() throws Exception{
        HostnameVerifier hv = new HostnameVerifier() {
            public boolean verify(String urlHostName, SSLSession session) {
                return true;
            }
        };
        trustAllHttpsCertificates();
        HttpsURLConnection.setDefaultHostnameVerifier(hv);
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
            ignoreSsl();	//在openConnection前调用该方法
            URLConnection urlConnection = new URL(url).openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8));
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
        File file = new File(path);
        BufferedReader reader = null;
        try {
            FileInputStream in = new FileInputStream(file);
            reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                json.append(inputLine);
            }
            reader.close();
        } catch (IOException e) {
            logger.error("load json string from local path failed", e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
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
        System.out.println(loadJsonStringFromLocalPath("F:\\aa.json"));
        System.out.println(loadJsonStringFromUrl("https://service.cela.gov.cn/system/studyinfo/file/url?signature=eyJzdWIiOiJPUEVOIiwiaXNzIjoiOGE5OThhMTU3MjcyODYwYTAxNzI3MmJlMzJmMjAwMjciLCJleHAiOjE1OTc4MTA4MzB9.bHjASnTEdIlhLSM-dCEl4h6QdtytIYVdQXVtIR4LPP4&token=3daa530bd9191b1c782148dc3de67e64&file_id=8a998a1474024c9801740250155f002b"));
    }
}
