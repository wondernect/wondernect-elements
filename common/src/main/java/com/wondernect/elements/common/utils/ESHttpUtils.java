package com.wondernect.elements.common.utils;

import eu.bitwalker.useragentutils.UserAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

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
        String ipAddress = request.getHeader("x-forwarded-for");
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){
                //根据网卡取本机配置的IP
                InetAddress inet;
                try {
                    inet = InetAddress.getLocalHost();
                    ipAddress= inet.getHostAddress();
                } catch (UnknownHostException e) {
                    logger.error("ESHttpUtils get ip address failed", e);
                }
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(ipAddress != null && ipAddress.length() > 15){ //"***.***.***.***".length() = 15
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
}
