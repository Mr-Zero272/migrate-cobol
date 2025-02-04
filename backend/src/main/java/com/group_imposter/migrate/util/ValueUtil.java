package com.group_imposter.migrate.util;

public class ValueUtil {
    public static long toLong(String substring) {
        return Long.parseLong(substring);
    }

    public static int toInt(String substring) {
        return Integer.parseInt(substring);
    }

    public static short toShort(String substring) {
        return Short.parseShort(substring);
    }
}
