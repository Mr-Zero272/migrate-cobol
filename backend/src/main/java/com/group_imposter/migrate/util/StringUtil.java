package com.group_imposter.migrate.util;

import com.group_imposter.migrate.constant.CompareResultConstant;

import java.nio.charset.Charset;
import java.util.Arrays;



public class StringUtil {

    public static final String EBCDIC_CHARSET = "IBM930";

    public static final char HIGH_VALUE = 0xFF;
    public static final char LOW_VALUE = 0x00;

    public static final boolean isSpace(String s) {
        if (s == null || s.length() == 0) {
            return false;
        } else {
            return s.equals(spaces(s.length()));
        }
    }

    public static final boolean isZero(String s) {
        if (s == null || s.length() == 0) {
            return false;
        } else {
            return s.equals(getZero(s.length()));
        }
    }

    public static final boolean isAll(String s, String all) {
        if (s == null || s.length() == 0) {
            return false;
        } else {
            return s.equals(getAll(all, s.length()));
        }
    }

    /***
     * To compare 2 string. Returned value is 0 if equal, 1 if str1 >str2, -1 if
     * str1<str2
     * 
     * @param str1
     * @param str2
     * @return
     */
    public static final int compare(String str1, String str2) {
        int result = CompareResultConstant.EQUAL;
        Charset ebcdicCharset = Charset.forName(EBCDIC_CHARSET);

        byte[] arr1 = str1.getBytes(ebcdicCharset);
        byte[] arr2 = str2.getBytes(ebcdicCharset);
        int code1, code2, i;

        for (i = 0; i < Math.min(arr1.length, arr2.length); i++) {
            code1 = (int) arr1[i] & 0xFF;
            code2 = (int) arr2[i] & 0xFF;

            if (code1 != code2) {
                result = (code1 - code2) / (Math.abs(code1 - code2));
                break;
            }
        }
        if (result == 0) {
            if (i < arr1.length) {
                result = isSpace(str1.substring(str2.length())) ? CompareResultConstant.EQUAL
                        : CompareResultConstant.GREATER_THAN;
            } else if (i < arr2.length) {
                result = isSpace(str2.substring(str1.length())) ? CompareResultConstant.EQUAL
                        : CompareResultConstant.LESS_THAN;
            }
        }
        return result;
    }

    /***
     * To get string of HIGH-VALUE in specific length
     * 
     * @param len
     * @return
     */
    public static final String getHighValue(int len) {
        byte[] bytes = new byte[len];
        Arrays.fill(bytes, (byte) 0xFF);
        return new String(bytes, Charset.forName(EBCDIC_CHARSET));
    }

    /***
     * To get string of LOW-VALUE in specific length
     * 
     * @param len
     * @return
     */
    public static final String getLowValue(int len) {
        byte[] bytes = new byte[len];
        Arrays.fill(bytes, (byte) 0x00);
        return new String(bytes, Charset.forName(EBCDIC_CHARSET));
    }

    /***
     * To get string of spaces in specific length
     * 
     * @param len
     * @return
     */
    public static final String spaces(int len) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < len; i++) {
            s.append(' ');
        }
        return s.toString();
    }

    /***
     * To padding value into right of a string
     * 
     * @param value
     * @param addValue
//     * @param addSize
     * @return
     */
    public static String paddingRight(String value, String addValue, int length) {
        if (value.length() >= length) {
            return value;
        }
        while (value.length() < length) {
            value = value + addValue;
        }
        return value;
    }

    /***
     * To add value into left of a string
     * 
     * @param value
     * @param addValue
//     * @param addSize
     * @return
     */
    public static String paddingLeft(String value, String addValue, int length) {
        if (value.length() >= length) {
            return value;
        }
        while (value.length() < length) {
            value = addValue + value;
        }
        return value;
    }

    public static String getAll(Object value, int length) {
        String rs = "";
        for (int i = 0; i < length; i++) {
            rs += String.valueOf(value);
        }
        return rs;
    }

    public static String getZero(int length) {
        String rs = "";
        for (int i = 0; i < length; i++) {
            rs += "0";
        }
        return rs;
    }
}
