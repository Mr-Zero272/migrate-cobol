package com.group_imposter.migrate.util;

public class FieldFormat {

    public static <T> String format(T obj, int length) {
        if (obj instanceof String str) {
            return str.length() >= length ? str : String.format("%-" + length + "s", str);
        } else if (obj instanceof Short shortVal) {
            return String.format("%0" + length + "d", shortVal);
        } else if (obj instanceof Integer intVal) {
            return String.format("%0" + length + "d", intVal);
        } else if (obj instanceof Float floatVal) {
            int intVal = floatVal.intValue();
            return String.format("%0" + length + "d", intVal);
        } else if (obj instanceof Double doubleVal) {
            int intVal = doubleVal.intValue();
            return String.format("%0" + length + "d", intVal);
        }
        return "";
    }
}
