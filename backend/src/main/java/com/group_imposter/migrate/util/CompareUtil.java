package com.group_imposter.migrate.util;

import constants.CompareResultConstant;
import constants.ValueConst;

public class CompareUtil {
    public static boolean eq(String str1, String str2) {
        return StringUtil.compare(str1, str2) == CompareResultConstant.EQUAL;
    }

    public static boolean ne(String str1, String str2) {
        return StringUtil.compare(str1, str2) != CompareResultConstant.EQUAL;
    }

    public static boolean lt(String str1, String str2) {
        return StringUtil.compare(str1, str2) < CompareResultConstant.EQUAL;
    }

    public static boolean le(String str1, String str2) {
        return StringUtil.compare(str1, str2) <= CompareResultConstant.EQUAL;
    }

    public static boolean gt(String str1, String str2) {
        return StringUtil.compare(str1, str2) > CompareResultConstant.EQUAL;
    }

    public static boolean ge(String str1, String str2) {
        return StringUtil.compare(str1, str2) >= CompareResultConstant.EQUAL;
    }

    public static int compareToZero(String str) {
        if (str == null || str.isEmpty()) {
            return CompareResultConstant.EQUAL;
        }
        for (int i = 0; i < str.length(); i++) {
            int tmp = StringUtil.compare(str.substring(i, i + 1), "0");
            if (tmp != CompareResultConstant.EQUAL) {
                return tmp;
            }
        }
        return CompareResultConstant.EQUAL;
    }

    public static int compareToSpace(String str) {
        if (str == null || str.isEmpty()) {
            return CompareResultConstant.EQUAL;
        }
        for (int i = 0; i < str.length(); i++) {
            int tmp = StringUtil.compare(str.substring(i, i + 1), ValueConst.SPACE);
            if (tmp != CompareResultConstant.EQUAL) {
                return tmp;
            }
        }
        return CompareResultConstant.EQUAL;
    }

    public static int compareToLowValue(String str) {
        if (str == null || str.isEmpty()) {
            return CompareResultConstant.EQUAL;
        }
        String lowValue = StringUtil.getLowValue(1);
        for (int i = 0; i < str.length(); i++) {
            int tmp = StringUtil.compare(str.substring(i, i + 1), lowValue);
            if (tmp != CompareResultConstant.EQUAL) {
                return tmp;
            }
        }
        return CompareResultConstant.EQUAL;
    }

    public static int compareToHighValue(String str) {
        if (str == null || str.isEmpty()) {
            return CompareResultConstant.EQUAL;
        }
        String highValue = StringUtil.getHighValue(1);
        for (int i = 0; i < str.length(); i++) {
            int tmp = StringUtil.compare(str.substring(i, i + 1), highValue);
            if (tmp != CompareResultConstant.EQUAL) {
                return tmp;
            }
        }
        return CompareResultConstant.EQUAL;
    }
}
