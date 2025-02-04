package com.group_imposter.migrate.util;

public class StringUtil {
    public static int compare(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return -1; // or any other value to indicate null comparison
        }
        return str1.compareTo(str2);
    }


}
