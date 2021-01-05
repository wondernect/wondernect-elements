package com.wondernect.elements.common.utils;

import eu.bitwalker.useragentutils.UserAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * wondernect.com
 * Created on 2018/4/25.
 * Esystem
 * @author sunbeam
 */
public final class ESHttpUtils {

    private static final Logger logger = LoggerFactory.getLogger(ESHttpUtils.class);

    /**
     * 获取request ip address
     */
    public static String getHttpRequestIP(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        // logger.info("X-Forwarded-For头部请求ip地址为:" + ipAddress);
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
            // logger.info("Proxy-Client-IP头部请求ip地址为:" + ipAddress);
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
            // logger.info("WL-Proxy-Client-IP头部请求ip地址为:" + ipAddress);
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            // logger.info("getRemoteAddr头部请求ip地址为:" + ipAddress);
            if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){
                //根据网卡取本机配置的IP
                InetAddress inet;
                try {
                    inet = InetAddress.getLocalHost();
                    ipAddress= inet.getHostAddress();
                    // logger.info("getHostAddress头部请求ip地址为:" + ipAddress);
                } catch (UnknownHostException e) {
                    logger.error("ESHttpUtils get ip address failed", e);
                }
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(ipAddress != null && ipAddress.length() > 15){
            //"***.***.***.***".length() = 15
            // logger.info("ipAddress多个请求ip地址为:" + ipAddress);
            if(ipAddress.indexOf(",") > 0){
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

    /**
     * 获取request user agent
     */
    public static String getHttpRequestUserAgent(HttpServletRequest request) {
        return getHttpRequestHeader(request,"User-Agent", "UNKNOWN");
    }

    /**
     * 获取设备类型
     */
    public static String getDevicePlatform(HttpServletRequest request) {
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        return userAgent.getOperatingSystem().getName();
    }

    /**
     * 获取设备唯一标识
     */
    public static String getDeviceIdentifier(HttpServletRequest request) {
        return getHttpRequestHeader(request, "Device-Identifier", "UNKNOWN");
    }

    /**
     * 获取request url
     */
    public static String getHttpRequestUrl(HttpServletRequest request) {
        StringBuffer url = request.getRequestURL();
        if (null == url) {
            return "UNKNOWN";
        }
        return url.toString();
    }

    /**
     * 获取request uri
     */
    public static String getHttpRequestURI(HttpServletRequest request) {
        String URI = request.getRequestURI();
        if (null == URI || "".equals(URI)) {
            URI = "UNKNOWN";
        }
        return URI;
    }

    /**
     * 获取request method
     */
    public static String getHttpRequestMethod(HttpServletRequest request) {
        String method = request.getMethod();
        if (null == method || "".equals(method)) {
            method = "UNKNOWN";
        }
        return method;
    }

    /**
     * 获取request header param
     */
    public static String getHttpRequestHeader(HttpServletRequest request, String paramKey, String defaultValue) {
        String param = request.getHeader(paramKey);
        if (null == param || "".equals(param)) {
            param = defaultValue;
        }
        return param;
    }

    /**
     * 获取request param
     */
    public static String getHttpRequestParameter(HttpServletRequest request, String paramKey, String defaultValue) {
        String param = request.getParameter(paramKey);
        if (null == param || "".equals(param)) {
            param = defaultValue;
        }
        return param;
    }

    private static class miTM implements TrustManager, X509TrustManager {
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

    public static InputStream getUrlConnectionInputStream(String url) {
        InputStream inputStream = null;
        try {
            ignoreSsl();	//在openConnection前调用该方法
            URLConnection urlConnection = new URL(url).openConnection();
            inputStream = urlConnection.getInputStream();
        } catch (Exception e) {
            logger.error("load input stream from url failed", e);
        }
        return inputStream;
    }
}
