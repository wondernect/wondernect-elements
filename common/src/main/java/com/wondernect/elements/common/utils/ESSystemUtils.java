package com.wondernect.elements.common.utils;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: ESSystemUtils
 * Author: chenxun
 * Date: 2019/3/22 14:30
 * Description: system utils
 */
public final class ESSystemUtils {

    private static final String OS_NAME_PROPERTY = "os.name";

    private static final String USER_DIR_PROPERTY = "user.dir";

    private static final String WINDOWS = "Windows";

    public static String getSystemPropertyValue(String propertyName) {
        return System.getProperties().getProperty(propertyName);
    }

    public static String getSystemPropertyValue(String propertyName, String defaultValue) {
        return System.getProperties().getProperty(propertyName, defaultValue);
    }

    public static String getSystemOSName() {
        return getSystemPropertyValue(OS_NAME_PROPERTY);
    }

    public static String getUserDir() {
        return getSystemPropertyValue(USER_DIR_PROPERTY);
    }

    public static Boolean isWindows() {
        String osName = getSystemOSName();
        if (osName == null) {
            return false;
        }
        return osName.contains(WINDOWS);
    }
}
