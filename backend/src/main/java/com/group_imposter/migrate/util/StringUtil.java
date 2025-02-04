package com.group_imposter.migrate.util;

public class StringUtil {
    public static int compare(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return (str1 == str2) ? 0 : (str1 == null ? -1 : 1);
        }
        return str1.compareTo(str2);
    }
}
