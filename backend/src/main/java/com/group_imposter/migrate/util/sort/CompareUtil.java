package com.group_imposter.migrate.util.sort;

import java.math.BigDecimal;
import java.math.BigInteger;

import constants.CompareResultConstant;
import util.StringUtil;

class CompareUtil {
    protected static int compare(String key1, String key2, String sortType, DataType dataType) {
        int result = CompareResultConstant.EQUAL;
        switch (dataType) {
            case String:
                result = StringUtil.compare(key1, key2);
                break;
            case Short:
                short short1 = Short.parseShort(key1);
                short short2 = Short.parseShort(key2);
                result = short1 > short2 ? CompareResultConstant.GREATER_THAN
                        : (short1 == short2 ? CompareResultConstant.EQUAL
                                : CompareResultConstant.LESS_THAN);
                break;
            case Int:
                int int1 = Integer.parseInt(key1);
                int int2 = Integer.parseInt(key2);
                result = int1 > int2 ? CompareResultConstant.GREATER_THAN
                        : (int1 == int2 ? CompareResultConstant.EQUAL
                                : CompareResultConstant.LESS_THAN);
                break;
            case Long:
                long long1 = Long.parseLong(key1);
                long long2 = Long.parseLong(key2);
                result = long1 > long2 ? CompareResultConstant.GREATER_THAN
                        : (long1 == long2 ? CompareResultConstant.EQUAL
                                : CompareResultConstant.LESS_THAN);
                break;
            case BigInteger:
                BigInteger bigInteger1 = new BigInteger(key1);
                BigInteger bigInteger2 = new BigInteger(key2);
                result = bigInteger1.compareTo(bigInteger2);
                break;
            case Float:
                float float1 = Float.parseFloat(key1);
                float float2 = Float.parseFloat(key2);
                result = float1 > float2 ? CompareResultConstant.GREATER_THAN
                        : (float1 == float2 ? CompareResultConstant.EQUAL
                                : CompareResultConstant.LESS_THAN);
                break;
            case Double:
                double double1 = Double.parseDouble(key1);
                double double2 = Double.parseDouble(key2);
                result = double1 > double2 ? CompareResultConstant.GREATER_THAN
                        : (double1 == double2 ? CompareResultConstant.EQUAL
                                : CompareResultConstant.LESS_THAN);
                break;
            case BigDecimal:
                BigDecimal bigDecimal1 = new BigDecimal(key1);
                BigDecimal bigDecimal2 = new BigDecimal(key2);
                result = bigDecimal1.compareTo(bigDecimal2);
                break;
        }
        if (!sortType.equals("A")) {
            result *= CompareResultConstant.LESS_THAN;
        }
        return result;
    }
}
