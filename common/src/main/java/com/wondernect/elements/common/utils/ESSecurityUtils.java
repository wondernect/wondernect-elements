package com.wondernect.elements.common.utils;

import org.apache.commons.codec.digest.DigestUtils;

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
}
