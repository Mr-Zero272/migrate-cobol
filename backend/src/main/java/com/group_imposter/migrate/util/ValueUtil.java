package com.group_imposter.migrate.util;

import java.math.BigDecimal;

import exception.InvalidFormatException;

public class ValueUtil {

    public static int toInt(String sValue) {
        return Integer.valueOf(sValue);
    }

    public static long toLong(String sValue) {
        return Long.valueOf(sValue);
    }

    public static short toShort(String sValue) {
        return Short.valueOf(sValue);
    }

    public static String toString(String sValue) {
        return sValue;
    }

    /***
     * Input value does not have dot point. Must break string to double value
     * Input value was read from parent's node value in structure
     * 
     * @param lengthBeforeDot
     * @param lengthAfterDot
     * @param sValue
     * @return
     * @throws InvalidFormatException
     */
    public static double toDouble(int lengthBeforeDot, int lengthAfterDot, String sValue) {
        BigDecimal value = toBigDecimal(lengthBeforeDot, lengthAfterDot, sValue);
        return value.doubleValue();
    }

    /***
     * Input value does not have dot point. Must break string to double value
     * Input value was read from parent's node value in structure
     * 
     * @param lengthBeforeDot
     * @param lengthAfterDot
     * @param sValue
     * @return
     * @throws InvalidFormatException
     */
    public static BigDecimal toBigDecimal(int lengthBeforeDot, int lengthAfterDot, String sValue) {
        if (sValue.length() > (lengthBeforeDot + lengthAfterDot)) {
            sValue = sValue.substring(sValue.length() - (lengthBeforeDot + lengthAfterDot),
                    lengthBeforeDot + lengthAfterDot);
        } else if (sValue.length() < (lengthBeforeDot + lengthAfterDot)) {
            sValue = StringUtil.paddingRight(sValue, "0", lengthBeforeDot + lengthAfterDot);
        }
        String beforeDotValue = sValue.substring(0, lengthBeforeDot);
        String afterDotValue = sValue.substring(lengthBeforeDot, lengthBeforeDot + lengthAfterDot);
        return new BigDecimal(beforeDotValue + "." + afterDotValue);
    }

    /***
     * To compare a string with HIGH-VALUE. if all characters in string is
     * HIGH-VALUE then return true else return false
     * 
     * @param s
     * @return
     */
    public static final boolean isHighValue(String s) {
        if (s == null || s.length() == 0) {
            return false;
        } else {
            return s.equals(StringUtil.getHighValue(s.length()));
        }
    }

    public static final boolean isHighValue(short param) {
        return param == Short.MAX_VALUE;
    }

    public static final boolean isHighValue(int param) {
        return param == Integer.MAX_VALUE;
    }

    public static final boolean isHighValue(long param) {
        return param == Long.MAX_VALUE;
    }

    public static final boolean isHighValue(float param) {
        return param == Float.MAX_VALUE;
    }

    public static final boolean isHighValue(double param) {
        return param == Double.MAX_VALUE;
    }

    public static final boolean isHighValue(BigDecimal param) {
        return param.doubleValue() == Double.MAX_VALUE;
    }

    /***
     * To compare a string with LOW-VALUE. if all characters in string is
     * LOW-VALUE then return true else return false
     * 
     * @param s
     * @return
     */
    public static final boolean isLowValue(String s) {
        if (s == null || s.length() == 0) {
            return false;
        } else {
            return s.equals(StringUtil.getLowValue(s.length()));
        }
    }

    public static final boolean isLowValue(short param) {
        return param == Short.MIN_VALUE;
    }

    public static final boolean isLowValue(int param) {
        return param == Integer.MIN_VALUE;
    }

    public static final boolean isLowValue(long param) {
        return param == Long.MIN_VALUE;
    }

    public static final boolean isLowValue(float param) {
        return param == Float.MIN_VALUE;
    }

    public static final boolean isLowValue(double param) {
        return param == Double.MIN_VALUE;
    }

    public static final boolean isLowValue(BigDecimal param) {
        return param.doubleValue() == Double.MIN_VALUE;
    }

    public static String convertToJavaClassName(String cobolPG) {
        String[] nameParts = cobolPG.split("-");
        for (int i = 0; i < nameParts.length; i++) {
            nameParts[i] = nameParts[i].substring(0, 1).toUpperCase()
                    + nameParts[i].substring(1).toLowerCase();
        }
        StringBuilder sbStr = new StringBuilder();
        for (int i = 0, il = nameParts.length; i < il; i++) {
            sbStr.append(nameParts[i]);
        }
        return sbStr.toString();
    }
}
