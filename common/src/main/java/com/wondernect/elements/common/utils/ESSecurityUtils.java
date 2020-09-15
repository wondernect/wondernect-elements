package com.wondernect.elements.common.utils;

import org.apache.commons.codec.digest.DigestUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * Created by cxhome on 2017/8/26.
 * wondernect.com
 * @author sunbeam
 */
public final class ESSecurityUtils {

    /**
     * 一次MD5加密 md5(value)
     */
    public static String md5Crypt(String origin) {
        return DigestUtils.md5Hex(origin);
    }

    /**
     * 二次MD5加密 md5(md5(value))
     */
    public static String doubleMd5Crypt(String origin) {
        return md5Crypt(md5Crypt(origin));
    }

    /**
     * base64 encode
     */
    public static String base64Encode(byte[] encrypted) {
        return new BASE64Encoder().encode(encrypted);
    }

    /**
     * base64 decode
     */
    public static byte[] base64Decode(String content) throws IOException {
        return new BASE64Decoder().decodeBuffer(content);
    }

    /**
     * 字节数组转成16进制字符串
     */
    public static String byte2hex(byte[] bytes) {
        StringBuilder content = new StringBuilder(bytes.length * 2);
        String tmp = "";
        for (int n = 0; n < bytes.length; n++) {
            // 整数转成十六进制表示
            tmp = (java.lang.Integer.toHexString(bytes[n] & 0XFF));
            if (tmp.length() == 1) {
                content.append("0");
            }
            content.append(tmp);
        }
        return content.toString().toUpperCase();
    }

    /**
     * 将hex字符串转换成字节数组
     */
    public static byte[] hex2byte(String content) {
        if (content == null || content.length() < 2) {
            return new byte[0];
        }
        content = content.toLowerCase();
        int l = content.length() / 2;
        byte[] result = new byte[l];
        for (int i = 0; i < l; ++i) {
            String tmp = content.substring(2 * i, 2 * i + 2);
            result[i] = (byte) (Integer.parseInt(tmp, 16) & 0xFF);
        }
        return result;
    }
}
