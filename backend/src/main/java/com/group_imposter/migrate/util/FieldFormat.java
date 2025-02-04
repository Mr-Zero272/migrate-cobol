package com.group_imposter.migrate.util;

import java.math.BigDecimal;

public class FieldFormat {
    public static <T> String format(int length, T obj) {
        if (obj instanceof String str) {
            return str.length() >= length ? str : String.format("%-" + length + "s", str);
        } else if (obj instanceof Short shortVal) {
            return String.format("%0" + length + "d", shortVal);
        } else if (obj instanceof Integer intVal) {
            return String.format("%0" + length + "d", intVal);
        } else if (obj instanceof BigDecimal bigDecimal) {
            int intVal = bigDecimal.intValue();
            return String.format("%0" + length + "d", intVal);
        }
        return "";
    }
    public static <T> String format(int length, boolean flag, T obj) {
        if (obj instanceof String str) {
            // Nếu flag = true, có thể trim hoặc xử lý đặc biệt
            return flag ? str.trim() : str.length() >= length ? str : String.format("%-" + length + "s", str);
        } else if (obj instanceof Short shortVal) {
            return String.format("%0" + length + "d", shortVal);
        } else if (obj instanceof Integer intVal) {
            return flag ? String.format("%" + length + "d", intVal) : String.format("%0" + length + "d", intVal);
        } else if (obj instanceof BigDecimal bigDecimal) {
            int intVal = bigDecimal.intValue();
            return flag ? String.format("%" + length + "d", intVal) : String.format("%0" + length + "d", intVal);
        }
        return "";
    }

}
