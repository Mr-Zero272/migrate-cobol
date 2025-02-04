package com.group_imposter.migrate.util;

public class ValueUtil {
    // Convert String to Short
    public static short toShort(String value) {
        try {
            return Short.parseShort(value.trim());
        } catch (NumberFormatException e) {
            return 0; // Default value if conversion fails
        }
    }

    // Convert String to Integer
    public static int toInt(String value) {
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            return 0; // Default value if conversion fails
        }
    }

    // Convert Integer to String with fixed length (padding with zeros)
    public static String formatInt(int value, int length) {
        return String.format("%0" + length + "d", value);
    }

    // Convert Short to String with fixed length (padding with zeros)
    public static String formatShort(short value, int length) {
        return String.format("%0" + length + "d", value);
    }

    public static final String SPACE = " ";
}
