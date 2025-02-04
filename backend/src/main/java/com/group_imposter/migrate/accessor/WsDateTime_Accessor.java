package com.group_imposter.migrate.accessor;

import com.group_imposter.migrate.constant.ValueConst;
import com.group_imposter.migrate.model.WsDateTime;
import com.group_imposter.migrate.util.FieldFormat;
import com.group_imposter.migrate.util.ValueUtil;


public class WsDateTime_Accessor {
    public static String getWsDateTime(WsDateTime wsDateTime) {
        StringBuilder sb = new StringBuilder();
        sb.append(FieldFormat.format(4, wsDateTime.getWsCurdateYear(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(2, wsDateTime.getWsCurdateMonth(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(2, wsDateTime.getWsCurdateDay(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(2, wsDateTime.getWsCurtimeHours(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(2, wsDateTime.getWsCurtimeMinute(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(2, wsDateTime.getWsCurtimeSecond(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(2, wsDateTime.getWsCurtimeMilsec(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(2, wsDateTime.getWsCurdateMm(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(1, wsDateTime.getFiller1(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(2, wsDateTime.getWsCurdateDd(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(1, wsDateTime.getFiller2(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(2, wsDateTime.getWsCurdateYy(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(2, wsDateTime.getWsCurtimeHh(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(1, wsDateTime.getFiller1OfWsCurtimeHhMmSs(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(2, wsDateTime.getWsCurtimeMm(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(1, wsDateTime.getFiller2OfWsCurtimeHhMmSs(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(2, wsDateTime.getWsCurtimeSs(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(4, wsDateTime.getWsTimestampDtYyyy(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(1, wsDateTime.getFiller1OfWsTimestamp(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(2, wsDateTime.getWsTimestampDtMm(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(1, wsDateTime.getFiller2OfWsTimestamp(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(2, wsDateTime.getWsTimestampDtDd(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(1, wsDateTime.getFiller3(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(2, wsDateTime.getWsTimestampTmHh(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(1, wsDateTime.getFiller4(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(2, wsDateTime.getWsTimestampTmMm(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(1, wsDateTime.getFiller5(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(2, wsDateTime.getWsTimestampTmSs(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(1, wsDateTime.getFiller6(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(6, wsDateTime.getWsTimestampTmMs6(), wsVariables.getWsRespCd()));
        return sb.toString();
    }


    public static void setWsDateTime(WsDateTime wsDateTime, String value) {
        value = FieldFormat.format(58, value, wsVariables.getWsRespCd());
        WsDateTime_Accessor.setWsCurdateData(wsDateTime, value.substring(0, 16));
        WsDateTime_Accessor.setWsCurdateMmDdYy(wsDateTime, value.substring(16, 24));
        WsDateTime_Accessor.setWsCurtimeHhMmSs(wsDateTime, value.substring(24, 32));
        WsDateTime_Accessor.setWsTimestamp(wsDateTime, value.substring(32, 58));
    }


    public static void initializeWsDateTime(WsDateTime wsDateTime) {
        WsDateTime_Accessor.initializeWsCurdateData(wsDateTime);
        WsDateTime_Accessor.initializeWsCurdateMmDdYy(wsDateTime);
        WsDateTime_Accessor.initializeWsCurtimeHhMmSs(wsDateTime);
        WsDateTime_Accessor.initializeWsTimestamp(wsDateTime);
    }


    public static String getWsCurdateData(WsDateTime wsDateTime) {
        StringBuilder sb = new StringBuilder();
        sb.append(FieldFormat.format(4, wsDateTime.getWsCurdateYear(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(2, wsDateTime.getWsCurdateMonth(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(2, wsDateTime.getWsCurdateDay(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(2, wsDateTime.getWsCurtimeHours(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(2, wsDateTime.getWsCurtimeMinute(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(2, wsDateTime.getWsCurtimeSecond(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(2, wsDateTime.getWsCurtimeMilsec(), wsVariables.getWsRespCd()));
        return sb.toString();
    }


    public static void setWsCurdateData(WsDateTime wsDateTime, String value) {
        value = FieldFormat.format(16, value, wsVariables.getWsRespCd());
        WsDateTime_Accessor.setWsCurdate(wsDateTime, value.substring(0, 8));
        WsDateTime_Accessor.setWsCurtime(wsDateTime, value.substring(8, 16));
    }


    public static void initializeWsCurdateData(WsDateTime wsDateTime) {
        WsDateTime_Accessor.initializeWsCurdate(wsDateTime);
        WsDateTime_Accessor.initializeWsCurtime(wsDateTime);
    }


    public static String getWsCurdateMmDdYy(WsDateTime wsDateTime) {
        StringBuilder sb = new StringBuilder();
        sb.append(FieldFormat.format(2, wsDateTime.getWsCurdateMm(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(1, wsDateTime.getFiller1(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(2, wsDateTime.getWsCurdateDd(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(1, wsDateTime.getFiller2(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(2, wsDateTime.getWsCurdateYy(), wsVariables.getWsRespCd()));
        return sb.toString();
    }


    public static void setWsCurdateMmDdYy(WsDateTime wsDateTime, String value) {
        value = FieldFormat.format(8, value, wsVariables.getWsRespCd());
        wsDateTime.setWsCurdateMm(ValueUtil.toShort(value.substring(0, 2)));
        wsDateTime.setFiller1(value.substring(2, 3));
        wsDateTime.setWsCurdateDd(ValueUtil.toShort(value.substring(3, 5)));
        wsDateTime.setFiller2(value.substring(5, 6));
        wsDateTime.setWsCurdateYy(ValueUtil.toShort(value.substring(6, 8)));
    }


    public static void initializeWsCurdateMmDdYy(WsDateTime wsDateTime) {
        wsDateTime.setWsCurdateMm((short) 0);
        wsDateTime.setFiller1(ValueConst.SPACE);
        wsDateTime.setWsCurdateDd((short) 0);
        wsDateTime.setFiller2(ValueConst.SPACE);
        wsDateTime.setWsCurdateYy((short) 0);
    }


    public static String getWsCurtimeHhMmSs(WsDateTime wsDateTime) {
        StringBuilder sb = new StringBuilder();
        sb.append(FieldFormat.format(2, wsDateTime.getWsCurtimeHh(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(1, wsDateTime.getFiller1OfWsCurtimeHhMmSs(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(2, wsDateTime.getWsCurtimeMm(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(1, wsDateTime.getFiller2OfWsCurtimeHhMmSs(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(2, wsDateTime.getWsCurtimeSs(), wsVariables.getWsRespCd()));
        return sb.toString();
    }


    public static void setWsCurtimeHhMmSs(WsDateTime wsDateTime, String value) {
        value = FieldFormat.format(8, value, wsVariables.getWsRespCd());
        wsDateTime.setWsCurtimeHh(ValueUtil.toShort(value.substring(0, 2)));
        wsDateTime.setFiller1OfWsCurtimeHhMmSs(value.substring(2, 3));
        wsDateTime.setWsCurtimeMm(ValueUtil.toShort(value.substring(3, 5)));
        wsDateTime.setFiller2OfWsCurtimeHhMmSs(value.substring(5, 6));
        wsDateTime.setWsCurtimeSs(ValueUtil.toShort(value.substring(6, 8)));
    }


    public static void initializeWsCurtimeHhMmSs(WsDateTime wsDateTime) {
        wsDateTime.setWsCurtimeHh((short) 0);
        wsDateTime.setFiller1OfWsCurtimeHhMmSs(ValueConst.SPACE);
        wsDateTime.setWsCurtimeMm((short) 0);
        wsDateTime.setFiller2OfWsCurtimeHhMmSs(ValueConst.SPACE);
        wsDateTime.setWsCurtimeSs((short) 0);
    }


    public static String getWsTimestamp(WsDateTime wsDateTime) {
        StringBuilder sb = new StringBuilder();
        sb.append(FieldFormat.format(4, wsDateTime.getWsTimestampDtYyyy(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(1, wsDateTime.getFiller1OfWsTimestamp(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(2, wsDateTime.getWsTimestampDtMm(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(1, wsDateTime.getFiller2OfWsTimestamp(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(2, wsDateTime.getWsTimestampDtDd(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(1, wsDateTime.getFiller3(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(2, wsDateTime.getWsTimestampTmHh(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(1, wsDateTime.getFiller4(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(2, wsDateTime.getWsTimestampTmMm(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(1, wsDateTime.getFiller5(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(2, wsDateTime.getWsTimestampTmSs(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(1, wsDateTime.getFiller6(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(6, wsDateTime.getWsTimestampTmMs6(), wsVariables.getWsRespCd()));
        return sb.toString();
    }


    public static void setWsTimestamp(WsDateTime wsDateTime, String value) {
        value = FieldFormat.format(26, value, wsVariables.getWsRespCd());
        wsDateTime.setWsTimestampDtYyyy(ValueUtil.toShort(value.substring(0, 4)));
        wsDateTime.setFiller1OfWsTimestamp(value.substring(4, 5));
        wsDateTime.setWsTimestampDtMm(ValueUtil.toShort(value.substring(5, 7)));
        wsDateTime.setFiller2OfWsTimestamp(value.substring(7, 8));
        wsDateTime.setWsTimestampDtDd(ValueUtil.toShort(value.substring(8, 10)));
        wsDateTime.setFiller3(value.substring(10, 11));
        wsDateTime.setWsTimestampTmHh(ValueUtil.toShort(value.substring(11, 13)));
        wsDateTime.setFiller4(value.substring(13, 14));
        wsDateTime.setWsTimestampTmMm(ValueUtil.toShort(value.substring(14, 16)));
        wsDateTime.setFiller5(value.substring(16, 17));
        wsDateTime.setWsTimestampTmSs(ValueUtil.toShort(value.substring(17, 19)));
        wsDateTime.setFiller6(value.substring(19, 20));
        wsDateTime.setWsTimestampTmMs6(ValueUtil.toInt(value.substring(20, 26)));
    }


    public static void initializeWsTimestamp(WsDateTime wsDateTime) {
        wsDateTime.setWsTimestampDtYyyy((short) 0);
        wsDateTime.setFiller1OfWsTimestamp(ValueConst.SPACE);
        wsDateTime.setWsTimestampDtMm((short) 0);
        wsDateTime.setFiller2OfWsTimestamp(ValueConst.SPACE);
        wsDateTime.setWsTimestampDtDd((short) 0);
        wsDateTime.setFiller3(ValueConst.SPACE);
        wsDateTime.setWsTimestampTmHh((short) 0);
        wsDateTime.setFiller4(ValueConst.SPACE);
        wsDateTime.setWsTimestampTmMm((short) 0);
        wsDateTime.setFiller5(ValueConst.SPACE);
        wsDateTime.setWsTimestampTmSs((short) 0);
        wsDateTime.setFiller6(ValueConst.SPACE);
        wsDateTime.setWsTimestampTmMs6(0);
    }


    public static String getWsCurdate(WsDateTime wsDateTime) {
        StringBuilder sb = new StringBuilder();
        sb.append(FieldFormat.format(4, wsDateTime.getWsCurdateYear(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(2, wsDateTime.getWsCurdateMonth(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(2, wsDateTime.getWsCurdateDay(), wsVariables.getWsRespCd()));
        return sb.toString();
    }


    public static void setWsCurdate(WsDateTime wsDateTime, String value) {
        value = FieldFormat.format(8, value, wsVariables.getWsRespCd());
        wsDateTime.setWsCurdateYear(ValueUtil.toShort(value.substring(0, 4)));
        wsDateTime.setWsCurdateMonth(ValueUtil.toShort(value.substring(4, 6)));
        wsDateTime.setWsCurdateDay(ValueUtil.toShort(value.substring(6, 8)));
    }


    public static void initializeWsCurdate(WsDateTime wsDateTime) {
        wsDateTime.setWsCurdateYear((short) 0);
        wsDateTime.setWsCurdateMonth((short) 0);
        wsDateTime.setWsCurdateDay((short) 0);
    }


    public static int getWsCurdateN(WsDateTime wsDateTime) {
        return Integer.parseInt(WsDateTime_Accessor.getWsDateTime(wsDateTime));
    }


    public static void setWsCurdateN(WsDateTime wsDateTime, int value) {
        WsDateTime_Accessor.setWsCurdate(wsDateTime, FieldFormat.format(8, value, wsVariables.getWsRespCd()));
    }


    public static void initializeWsCurdateN(WsDateTime wsDateTime) {
        WsDateTime_Accessor.setWsCurdate(wsDateTime, ValueConst.SPACE);
    }


    public static String getWsCurtime(WsDateTime wsDateTime) {
        StringBuilder sb = new StringBuilder();
        sb.append(FieldFormat.format(2, wsDateTime.getWsCurtimeHours(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(2, wsDateTime.getWsCurtimeMinute(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(2, wsDateTime.getWsCurtimeSecond(), wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(2, wsDateTime.getWsCurtimeMilsec(), wsVariables.getWsRespCd()));
        return sb.toString();
    }


    public static void setWsCurtime(WsDateTime wsDateTime, String value) {
        value = FieldFormat.format(8, value, wsVariables.getWsRespCd());
        wsDateTime.setWsCurtimeHours(ValueUtil.toShort(value.substring(0, 2)));
        wsDateTime.setWsCurtimeMinute(ValueUtil.toShort(value.substring(2, 4)));
        wsDateTime.setWsCurtimeSecond(ValueUtil.toShort(value.substring(4, 6)));
        wsDateTime.setWsCurtimeMilsec(ValueUtil.toShort(value.substring(6, 8)));
    }


    public static void initializeWsCurtime(WsDateTime wsDateTime) {
        wsDateTime.setWsCurtimeHours((short) 0);
        wsDateTime.setWsCurtimeMinute((short) 0);
        wsDateTime.setWsCurtimeSecond((short) 0);
        wsDateTime.setWsCurtimeMilsec((short) 0);
    }


    public static int getWsCurtimeN(WsDateTime wsDateTime) {
        return Integer.parseInt(WsDateTime_Accessor.getWsDateTime(wsDateTime));
    }


    public static void setWsCurtimeN(WsDateTime wsDateTime, int value) {
        WsDateTime_Accessor.setWsCurtime(wsDateTime, FieldFormat.format(8, value, wsVariables.getWsRespCd()));
    }


    public static void initializeWsCurtimeN(WsDateTime wsDateTime) {
        WsDateTime_Accessor.setWsCurtime(wsDateTime, ValueConst.SPACE);
    }
}
