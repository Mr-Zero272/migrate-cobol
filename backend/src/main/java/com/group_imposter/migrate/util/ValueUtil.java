package com.group_imposter.migrate.util;

public class ValueUtil {
    public static Long toLong(String value) {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            // Handle the exception, maybe log it or return a default value
            return 0L;
        }
    }

    public static Short toShort(String value) {
        try {
            return Short.parseShort(value);
        } catch (NumberFormatException e) {
            // Handle the exception, maybe log it or return a default value
            return 0;
        }
    }

    public static Integer toInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            // Handle the exception, maybe log it or return a default value
            return 0;
        }
    }

}
