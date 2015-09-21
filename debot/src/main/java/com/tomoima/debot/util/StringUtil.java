package com.tomoima.debot.util;

public class StringUtil {
    public static boolean isBlankOrNull(String str) {
        return (str == null || str.trim().length() < 1);
    }
}
