package com.group_imposter.migrate.accessor;

import com.group_imposter.migrate.constant.ValueConst;
import com.group_imposter.migrate.model.CarddemoCommarea;
import com.group_imposter.migrate.util.FieldFormat;
import com.group_imposter.migrate.util.StringUtil;
import com.group_imposter.migrate.util.ValueUtil;

public class CarddemoCommarea_Accessor {


    public static String getCarddemoCommarea(CarddemoCommarea carddemoCommarea){
        StringBuilder sb = new StringBuilder();
        sb.append(FieldFormat.format(4, carddemoCommarea.getCdemoFromTranid()));
        sb.append(FieldFormat.format(8, carddemoCommarea.getCdemoFromProgram()));
        sb.append(FieldFormat.format(4, carddemoCommarea.getCdemoToTranid()));
        sb.append(FieldFormat.format(8, carddemoCommarea.getCdemoToProgram()));
        sb.append(FieldFormat.format(8, carddemoCommarea.getCdemoUserId()));
        sb.append(FieldFormat.format(1, carddemoCommarea.getCdemoUserType()));
        sb.append(FieldFormat.format(1, carddemoCommarea.getCdemoPgmContext()));
        sb.append(FieldFormat.format(9, carddemoCommarea.getCdemoCustId()));
        sb.append(FieldFormat.format(25, carddemoCommarea.getCdemoCustFname()));
        sb.append(FieldFormat.format(25, carddemoCommarea.getCdemoCustMname()));
        sb.append(FieldFormat.format(25, carddemoCommarea.getCdemoCustLname()));
        sb.append(FieldFormat.format(11, carddemoCommarea.getCdemoAcctId()));
        sb.append(FieldFormat.format(1, carddemoCommarea.getCdemoAcctStatus()));
        sb.append(FieldFormat.format(16, carddemoCommarea.getCdemoCardNum()));
        sb.append(FieldFormat.format(7, carddemoCommarea.getCdemoLastMap()));
        sb.append(FieldFormat.format(7, carddemoCommarea.getCdemoLastMapset()));
        return sb.toString();
    }


    public static void setCarddemoCommarea(CarddemoCommarea carddemoCommarea, String value){
        value = FieldFormat.format(160, value);
        CarddemoCommarea_Accessor.setCdemoGeneralInfo(carddemoCommarea, value.substring(0, 34));
        CarddemoCommarea_Accessor.setCdemoCustomerInfo(carddemoCommarea, value.substring(34, 118));
        CarddemoCommarea_Accessor.setCdemoAccountInfo(carddemoCommarea, value.substring(118, 130));
        CarddemoCommarea_Accessor.setCdemoCardInfo(carddemoCommarea, value.substring(130, 146));
        CarddemoCommarea_Accessor.setCdemoMoreInfo(carddemoCommarea, value.substring(146, 160));
    }


    public static void initializeCarddemoCommarea(CarddemoCommarea carddemoCommarea){
        CarddemoCommarea_Accessor.initializeCdemoGeneralInfo(carddemoCommarea);
        CarddemoCommarea_Accessor.initializeCdemoCustomerInfo(carddemoCommarea);
        CarddemoCommarea_Accessor.initializeCdemoAccountInfo(carddemoCommarea);
        CarddemoCommarea_Accessor.initializeCdemoCardInfo(carddemoCommarea);
        CarddemoCommarea_Accessor.initializeCdemoMoreInfo(carddemoCommarea);
    }


    public static String getCdemoGeneralInfo(CarddemoCommarea carddemoCommarea){
        StringBuilder sb = new StringBuilder();
        sb.append(FieldFormat.format(4, carddemoCommarea.getCdemoFromTranid()));
        sb.append(FieldFormat.format(8, carddemoCommarea.getCdemoFromProgram()));
        sb.append(FieldFormat.format(4, carddemoCommarea.getCdemoToTranid()));
        sb.append(FieldFormat.format(8, carddemoCommarea.getCdemoToProgram()));
        sb.append(FieldFormat.format(8, carddemoCommarea.getCdemoUserId()));
        sb.append(FieldFormat.format(1, carddemoCommarea.getCdemoUserType()));
        sb.append(FieldFormat.format(1, carddemoCommarea.getCdemoPgmContext()));
        return sb.toString();
    }


    public static void setCdemoGeneralInfo(CarddemoCommarea carddemoCommarea, String value){
        value = FieldFormat.format(34, value);
        carddemoCommarea.setCdemoFromTranid(value.substring(0, 4));
        carddemoCommarea.setCdemoFromProgram(value.substring(4, 12));
        carddemoCommarea.setCdemoToTranid(value.substring(12, 16));
        carddemoCommarea.setCdemoToProgram(value.substring(16, 24));
        carddemoCommarea.setCdemoUserId(value.substring(24, 32));
        carddemoCommarea.setCdemoUserType(value.substring(32, 33));
        carddemoCommarea.setCdemoPgmContext(ValueUtil.toShort(value.substring(33, 34)));
    }


    public static void initializeCdemoGeneralInfo(CarddemoCommarea carddemoCommarea){
        carddemoCommarea.setCdemoFromTranid(FieldFormat.format(4, ValueConst.SPACE));
        carddemoCommarea.setCdemoFromProgram(FieldFormat.format(8, ValueConst.SPACE));
        carddemoCommarea.setCdemoToTranid(FieldFormat.format(4, ValueConst.SPACE));
        carddemoCommarea.setCdemoToProgram(FieldFormat.format(8, ValueConst.SPACE));
        carddemoCommarea.setCdemoUserId(FieldFormat.format(8, ValueConst.SPACE));
        carddemoCommarea.setCdemoUserType(ValueConst.SPACE);
        carddemoCommarea.setCdemoPgmContext((short)0);
    }


    public static String getCdemoCustomerInfo(CarddemoCommarea carddemoCommarea){
        StringBuilder sb = new StringBuilder();
        sb.append(FieldFormat.format(9, carddemoCommarea.getCdemoCustId()));
        sb.append(FieldFormat.format(25, carddemoCommarea.getCdemoCustFname()));
        sb.append(FieldFormat.format(25, carddemoCommarea.getCdemoCustMname()));
        sb.append(FieldFormat.format(25, carddemoCommarea.getCdemoCustLname()));
        return sb.toString();
    }


    public static void setCdemoCustomerInfo(CarddemoCommarea carddemoCommarea, String value){
        value = FieldFormat.format(84, value);
        carddemoCommarea.setCdemoCustId(ValueUtil.toInt(value.substring(0, 9)));
        carddemoCommarea.setCdemoCustFname(value.substring(9, 34));
        carddemoCommarea.setCdemoCustMname(value.substring(34, 59));
        carddemoCommarea.setCdemoCustLname(value.substring(59, 84));
    }


    public static void initializeCdemoCustomerInfo(CarddemoCommarea carddemoCommarea){
        carddemoCommarea.setCdemoCustId(0);
        carddemoCommarea.setCdemoCustFname(FieldFormat.format(25, ValueConst.SPACE));
        carddemoCommarea.setCdemoCustMname(FieldFormat.format(25, ValueConst.SPACE));
        carddemoCommarea.setCdemoCustLname(FieldFormat.format(25, ValueConst.SPACE));
    }


    public static String getCdemoAccountInfo(CarddemoCommarea carddemoCommarea){
        StringBuilder sb = new StringBuilder();
        sb.append(FieldFormat.format(11, carddemoCommarea.getCdemoAcctId()));
        sb.append(FieldFormat.format(1, carddemoCommarea.getCdemoAcctStatus()));
        return sb.toString();
    }


    public static void setCdemoAccountInfo(CarddemoCommarea carddemoCommarea, String value){
        value = FieldFormat.format(12, value);
        carddemoCommarea.setCdemoAcctId(ValueUtil.toLong(value.substring(0, 11)));
        carddemoCommarea.setCdemoAcctStatus(value.substring(11, 12));
    }


    public static void initializeCdemoAccountInfo(CarddemoCommarea carddemoCommarea){
        carddemoCommarea.setCdemoAcctId(0);
        carddemoCommarea.setCdemoAcctStatus(ValueConst.SPACE);
    }


    public static String getCdemoCardInfo(CarddemoCommarea carddemoCommarea){
        StringBuilder sb = new StringBuilder();
        sb.append(FieldFormat.format(16, carddemoCommarea.getCdemoCardNum()));
        return sb.toString();
    }


    public static void setCdemoCardInfo(CarddemoCommarea carddemoCommarea, String value){
        value = FieldFormat.format(16, value);
        carddemoCommarea.setCdemoCardNum(ValueUtil.toLong(value.substring(0, 16)));
    }


    public static void initializeCdemoCardInfo(CarddemoCommarea carddemoCommarea){
        carddemoCommarea.setCdemoCardNum(0);
    }


    public static String getCdemoMoreInfo(CarddemoCommarea carddemoCommarea){
        StringBuilder sb = new StringBuilder();
        sb.append(FieldFormat.format(7, carddemoCommarea.getCdemoLastMap()));
        sb.append(FieldFormat.format(7, carddemoCommarea.getCdemoLastMapset()));
        return sb.toString();
    }


    public static void setCdemoMoreInfo(CarddemoCommarea carddemoCommarea, String value){
        value = FieldFormat.format(14, value);
        carddemoCommarea.setCdemoLastMap(value.substring(0, 7));
        carddemoCommarea.setCdemoLastMapset(value.substring(7, 14));
    }


    public static void initializeCdemoMoreInfo(CarddemoCommarea carddemoCommarea){
        carddemoCommarea.setCdemoLastMap(FieldFormat.format(7, ValueConst.SPACE));
        carddemoCommarea.setCdemoLastMapset(FieldFormat.format(7, ValueConst.SPACE));
    }


    public static boolean isCdemoUsrtypAdmin(CarddemoCommarea carddemoCommarea){
        String value = carddemoCommarea.getCdemoUserType();
        if (StringUtil.compare(value, "A") == 0){
            return true;
        }
        return false;
    }


    public static boolean isCdemoUsrtypUser(CarddemoCommarea carddemoCommarea){
        String value = carddemoCommarea.getCdemoUserType();
        if (StringUtil.compare(value, "U") == 0){
            return true;
        }
        return false;
    }


    public static void setCdemoUsrtypAdmin(CarddemoCommarea carddemoCommarea){
        carddemoCommarea.setCdemoUserType("A");
    }


    public static void setCdemoUsrtypUser(CarddemoCommarea carddemoCommarea){
        carddemoCommarea.setCdemoUserType("U");
    }


    public static boolean isCdemoPgmEnter(CarddemoCommarea carddemoCommarea){
        short value = carddemoCommarea.getCdemoPgmContext();
        if (value == 0){
            return true;
        }
        return false;
    }


    public static boolean isCdemoPgmReenter(CarddemoCommarea carddemoCommarea){
        short value = carddemoCommarea.getCdemoPgmContext();
        if (value == 1){
            return true;
        }
        return false;
    }


    public static void setCdemoPgmEnter(CarddemoCommarea carddemoCommarea){
        carddemoCommarea.setCdemoPgmContext((short)0);
    }


    public static void setCdemoPgmReenter(CarddemoCommarea carddemoCommarea){
        carddemoCommarea.setCdemoPgmContext((short)1);
    }
}
