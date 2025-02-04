package com.group_imposter.migrate.util;

public class ConditionUtil {
    public static boolean isNumeric(Object o) {
        if (o == null) {
            return false;
        }
        return o instanceof Number;
    }

    public static boolean isAlphabetic(Object o) {
        if (o == null) {
            return false;
        }
        return String.valueOf(o).matches("[a-zA-Z]+");
    }

    public static boolean isAlphabeticLower(Object o) {
        if (o == null) {
            return false;
        }
        return String.valueOf(o).matches("[a-z]+");
    }

    public static boolean isAlphabeticUpper(Object o) {
        if (o == null) {
            return false;
        }
        return String.valueOf(o).matches("[A-Z]+");
    }

    public static boolean isDbcs(Object o) {
        return false;
    }

    public static boolean isKanji(Object o) {
        return false;
    }
}
