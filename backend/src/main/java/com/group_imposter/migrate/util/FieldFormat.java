package com.group_imposter.migrate.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

/***
 * To create format string for data that map with declaration in Cobol
 * 
 * @author NamTH
 * 
 */
public class FieldFormat {

    /***
     * To format a BigDecimal value in string. Append 0 into head integer part
     * and append 0 into tail of decimal part
     * 
     * @param sizeBeforeDot
     * @param sizeAfterDot
     * @param value
     * @return
     */
    public static String format(int sizeBeforeDot, int sizeAfterDot, BigDecimal value) {
        return format(sizeBeforeDot, sizeAfterDot, false, value);
    }

    public static String format(int sizeBeforeDot, int sizeAfterDot, double value) {
        return format(sizeBeforeDot, sizeAfterDot, false, value);
    }

    public static String format(int length, long value) {
        return format(length, false, value);
    }

    public static String format(int length, int value) {
        return format(length, false, value);
    }

    public static String format(int length, short value) {
        return format(length, false, value);
    }

    public static String format(int sizeBeforeDot, int sizeAfterDot, boolean isSigned,
            BigDecimal value) {
        String sValue = String.valueOf(value);
        String arr[] = sValue.split("\\.");
        String valueBeforeDot = format(sizeBeforeDot, isSigned, Integer.valueOf(arr[0]));
        String valueAfterDot = "";
        if (arr.length > 1) {
            valueAfterDot = arr[1];
        } else {
            valueAfterDot = "0";
        }
        if (valueAfterDot.length() < sizeAfterDot) {
            valueAfterDot = StringUtil.paddingRight(valueAfterDot, "0", sizeAfterDot);
        } else if (valueAfterDot.length() > sizeAfterDot) {
            valueAfterDot = valueAfterDot.substring(0, sizeAfterDot);
        }
        return valueBeforeDot + "." + valueAfterDot;
    }

    public static String format(int sizeBeforeDot, int sizeAfterDot, boolean isSigned,
            double value) {
        BigDecimal bigValue = new BigDecimal(String.valueOf(value));
        return format(sizeBeforeDot, sizeAfterDot, isSigned, bigValue);
    }

    public static String format(int length, boolean isSigned, long value) {
        String sValue = String.valueOf(value);
        if (!isSigned) {
            if (sValue.charAt(0) == '+' || sValue.charAt(0) == '-') {
                sValue = sValue.substring(1);
            }
            if (sValue.length() > length) {
                // get from tail
                return sValue.substring(sValue.length() - length, sValue.length());
            } else if (sValue.length() < length) {
                // padding 0
                return StringUtil.paddingLeft(sValue, "0", length);
            } else {
                return sValue;
            }
        } else {
            try {
                Integer.parseInt(sValue.substring(0, 1));
                return format(length, false, Long.parseLong(sValue));
            } catch (NumberFormatException e) {
                String sign = sValue.substring(0, 1);
                sValue = sValue.substring(1);
                return sign + format(length, false, Long.parseLong(sValue));
            }
        }
    }

    public static String format(int length, boolean isSigned, int value) {
        long longValue = (long) value;
        return format(length, isSigned, longValue);
    }

    public static String format(int length, boolean isSigned, short value) {
        long longValue = (long) value;
        return format(length, isSigned, longValue);
    }

    /***
     * Padding spaces into tail
     * 
     * @param length
     * @param value
     * @return
     */
    public static String format(int length, String value) {
        return String.format("%-" + length + "s", value);

    }

    /**
     * Format edited value to display format
     * 
     * @param num
     * @param format
     * @return
     */
    public static String formatToEditedValue(short num, String format) {
        return formatToEditedValue(String.valueOf(num), format);
    }

    /**
     * Format edited value to display format
     * 
     * @param num
     * @param format
     * @return
     */
    public static String formatToEditedValue(int num, String format) {
        return formatToEditedValue(String.valueOf(num), format);
    }

    /**
     * Format edited value to display format
     * 
     * @param num
     * @param format
     * @return
     */
    public static String formatToEditedValue(long num, String format) {
        return formatToEditedValue(String.valueOf(num), format);
    }

    /**
     * Format edited value to display format
     * 
     * @param num
     * @param format
     * @return
     */
    public static String formatToEditedValue(float num, String format) {
        return formatToEditedValue(String.valueOf(num), format);
    }

    /**
     * Format edited value to display format
     * 
     * @param num
     * @param format
     * @return
     */
    public static String formatToEditedValue(double num, String format) {
        return formatToEditedValue(String.valueOf(num), format);
    }

    /**
     * Format edited value to display format
     * 
     * @param num
     * @param format
     * @return
     */
    public static String formatToEditedValue(BigDecimal num, String format) {
        return formatToEditedValue(String.valueOf(num), format);
    }

    /**
     * Convert dataPictureString short to Full 9(03)V9(02) --> 999.99
     * -Z(02).Z(02)9 --> -ZZ.ZZ9
     * 
     * @param dataPictureString
     * @return
     */
    private static String reformatPictureString(String dataPictureString) {
        // add all char to stack
        ArrayList<String> queueOrigin = new ArrayList<>(
                Arrays.asList(dataPictureString.split("(?!^)")));
        StringBuilder sbNumber = new StringBuilder();
        StringBuilder pictureFull = new StringBuilder();
        while (queueOrigin.size() > 0) {
            String topChar = queueOrigin.get(0);
            queueOrigin.remove(0);
            switch (topChar) {
                case "(": {
                    sbNumber.append("0");
                    break;
                }
                case ")": {
                    String strEnd = pictureFull.substring(pictureFull.length() - 1,
                            pictureFull.length());
                    try {
                        for (int i = 0; i < Integer.parseInt(sbNumber.toString()) - 1; i++) {
                            pictureFull.append(strEnd);
                        }
                    } catch (Exception e) {
                        System.out
                                .println("Picture edited value convert fail :" + dataPictureString);
                        return null;
                    }
                    sbNumber = new StringBuilder();
                    break;
                }
                case "V": {
                    pictureFull.append(".");
                    break;
                }

                default: {
                    if (sbNumber.length() > 0) {
                        sbNumber.append(topChar);
                    } else {
                        pictureFull.append(topChar);
                    }
                    break;
                }
            }
        }
        return pictureFull.toString();
    }

    /**
     * check invalid input Occurrence DOT in format or number
     * 
     * @param str
     * @return
     */
    private static boolean isInvalidInput(String str) {
        int cnt = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.substring(i, i + 1).equals(".")) {
                cnt++;
                if (cnt > 1)
                    return true;
            }
        }
        return false;
    }

    /**
     * remove sign, remove 0 of number
     * 
     * @param number
     * @return
     */
    private static String reformatNumber(String number) {
        // remove sign
        if (number.indexOf("-") >= 0 || number.indexOf("+") >= 0) {
            number = number.trim().substring(1);
        }

        // remove 0 of number
        while (number.startsWith("0")) {
            number = number.substring(1);
        }
        if (number.indexOf(".") >= 0) {
            while (number.endsWith("0")) {
                number = number.substring(0, number.length() - 1);
            }
        }

        return number;
    }

    /**
     * Format edited value to display format Note : default sign : left of
     * number
     * 
     * @param num
     * @param format
     * @return
     */
    public static String formatToEditedValue(String number, String format) {
        // check invalid input
        if (isInvalidInput(number) || isInvalidInput(format)) {
            System.out.println("Invalid number or format");
            return number;
        }

        // convert picture to picture full
        format = reformatPictureString(format.trim().toUpperCase());

        // extract sign
        String sign = "";
        if (number.indexOf("-") >= 0) {
            sign = "-";
        } else if (number.indexOf("+") >= 0) {
            sign = "+";
        }

        number = reformatNumber(number.trim());

        // remove CR DB
        boolean existCRDB = false;
        boolean isCR = false;

        if (format.endsWith("CR")) {
            isCR = true;
            existCRDB = true;
            format = format.substring(0, format.length() - 2);
        }
        if (format.endsWith("DB")) {
            isCR = false;
            existCRDB = true;
            format = format.substring(0, format.length() - 2);
        }

        // extract decimal , fraction
        String decimal = "";
        String decimalFormat = "";
        String fraction = "";
        String fractionFormat = "";

        if (number.indexOf(".") < 0) {
            decimal = number;
        } else {
            decimal = number.substring(0, number.indexOf("."));
            fraction = number.substring(number.indexOf(".") + 1);
        }
        if (format.indexOf(".") < 0) {
            decimalFormat = format;
        } else {
            decimalFormat = "." + format.substring(0, format.indexOf("."));
            fractionFormat = format.substring(format.indexOf(".") + 1);
        }

        // add decimalPart to queue
        ArrayList<String> numberQueue = new ArrayList<>(
                Arrays.asList(new StringBuilder(decimal).reverse().toString().split("(?!^)")));
        ArrayList<String> formatQueue = new ArrayList<>(Arrays
                .asList(new StringBuilder(decimalFormat).reverse().toString().split("(?!^)")));
        String result = "";

        // move number to format
        boolean isBeforeDotFormat = true;
        boolean addDola = true;
        boolean addSign = true;
        boolean addedNum = false;
        while (formatQueue.size() > 0) {
            String topFormat = formatQueue.remove(0);
            String topNumber = "";
            if (numberQueue.size() > 0) {
                topNumber = numberQueue.get(0);
            }
            String addStr = "";
            switch (topFormat) {
                case "9": {
                    if (!"".equals(topNumber)) {
                        addStr = topNumber;
                        numberQueue.remove(0);
                    } else {
                        addStr = "0";
                    }
                    addedNum = true;
                    break;
                }
                case "Z": {
                    if (!"".equals(topNumber)) {
                        addStr = topNumber;
                        numberQueue.remove(0);
                    } else {
                        addStr = " ";
                    }
                    break;
                }
                case "*": {
                    if (!"".equals(topNumber)) {
                        addStr = topNumber;
                        numberQueue.remove(0);
                    } else {
                        addStr = "*";
                    }
                    break;
                }
                case "B": {
                    addStr = " ";
                    break;
                }
                case ",": {
                    if (!"".equals(topNumber)
                            || (formatQueue.size() > 0 && formatQueue.get(0).equals("9"))) {
                        addStr = ",";
                    } else if (formatQueue.size() > 0 && formatQueue.get(0).equals("*")) {
                        addStr = "*";
                    } else if (formatQueue.size() > 0 && formatQueue.get(0).equals("$")
                            && addDola) {
                        addStr = "$";
                        addDola = false;
                    } else {
                        addStr = " ";
                    }
                    break;
                }
                case "$": {
                    // 1234 --> format $999 --> $234
                    if (!"".equals(topNumber) && formatQueue.size() > 0) {
                        addStr = topNumber;
                        addedNum = true;
                        numberQueue.remove(0);
                        break;
                    }
                    // check length fraction for case "0000", "$ZZZZZ" -->
                    // "ssssss" S : space
                    // check addedNum for case "57397", "$$,$$9" --> "$7,397
                    if (addDola && (addedNum || fraction.length() > 0)) {
                        addStr = "$";
                        addDola = false;
                        break;
                    }
                    addStr = " ";
                    break;
                }
                case "-": {
                    if (!"".equals(topNumber) && addedNum && formatQueue.size() > 0) {
                        addStr = topNumber;
                        numberQueue.remove(0);
                        break;
                    }
                    if (("-".equals(sign) && addSign) || !addedNum) {
                        addStr = "-";
                        addSign = false;
                    } else {
                        addStr = " ";
                    }
                    break;
                }
                case "+": {
                    if (!"".equals(topNumber) && addedNum && formatQueue.size() > 0) {
                        addStr = topNumber;
                        numberQueue.remove(0);
                        break;
                    }
                    if ("-".equals(sign) && addSign) {
                        addStr = "-";
                        addSign = false;
                    } else if (addSign || !addedNum) {
                        addStr = "+";
                        addSign = false;
                    } else {
                        addStr = " ";
                    }
                    break;
                }
                case ".": {
                    isBeforeDotFormat = false;
                    addStr = ".";
                    // add fraction to queue
                    numberQueue = new ArrayList<>(Arrays.asList(fraction.split("(?!^)")));
                    formatQueue = new ArrayList<>(Arrays.asList(fractionFormat.split("(?!^)")));
                    break;
                }
                default: {
                    addStr = topFormat;
                    break;
                }
            }

            // add to result
            if (isBeforeDotFormat) {
                result = addStr.concat(result);
            } else {
                result = result.concat(addStr);
            }
        }

        // add CR DB if data is negative
        if (existCRDB) {
            if (sign.equals("-")) {
                if (isCR) {
                    result = result.concat("CR");
                } else {
                    result = result.concat("DB");
                }
            } else {
                result = result.concat("  ");
            }
        }
        return result;
    }

}
