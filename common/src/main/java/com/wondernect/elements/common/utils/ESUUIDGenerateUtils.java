package com.wondernect.elements.common.utils;

import java.util.UUID;

/**
 * Created by cxhome on 2017/8/20.
 * wondernect.com
 * @author sunbeam
 */
public final class ESUUIDGenerateUtils {

    /**
     * UUID生成
     */
    public static String generateIdentifier() {
        return UUID.randomUUID().toString();
    }

    /**
     * UUID生成
     */
    public static String generateIdentifier(String replacement) {
        return UUID.randomUUID().toString().replace("-", replacement);
    }
}
