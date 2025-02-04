package com.group_imposter.migrate.accessor;

import com.group_imposter.migrate.constant.ValueConst;
import com.group_imposter.migrate.model.SecUserData;
import com.group_imposter.migrate.util.FieldFormat;

public class SecUserData_Accessor {
    public static String getSecUserData(SecUserData secUserData){
        StringBuilder sb = new StringBuilder();
        sb.append(FieldFormat.format(8, secUserData.getSecUsrId()));
        sb.append(FieldFormat.format(20, secUserData.getSecUsrFname()));
        sb.append(FieldFormat.format(20, secUserData.getSecUsrLname()));
        sb.append(FieldFormat.format(8, secUserData.getSecUsrPwd()));
        sb.append(FieldFormat.format(1, secUserData.getSecUsrType()));
        sb.append(FieldFormat.format(23, secUserData.getSecUsrFiller()));
        return sb.toString();
    }


    public static void setSecUserData(SecUserData secUserData, String value){
        value = FieldFormat.format(80, value);
        secUserData.setSecUsrId(value.substring(0, 8));
        secUserData.setSecUsrFname(value.substring(8, 28));
        secUserData.setSecUsrLname(value.substring(28, 48));
        secUserData.setSecUsrPwd(value.substring(48, 56));
        secUserData.setSecUsrType(value.substring(56, 57));
        secUserData.setSecUsrFiller(value.substring(57, 80));
    }


    public static void initializeSecUserData(SecUserData secUserData){
        secUserData.setSecUsrId(FieldFormat.format(8, ValueConst.SPACE));
        secUserData.setSecUsrFname(FieldFormat.format(20, ValueConst.SPACE));
        secUserData.setSecUsrLname(FieldFormat.format(20, ValueConst.SPACE));
        secUserData.setSecUsrPwd(FieldFormat.format(8, ValueConst.SPACE));
        secUserData.setSecUsrType(ValueConst.SPACE);
        secUserData.setSecUsrFiller(FieldFormat.format(23, ValueConst.SPACE));
    }
//
}
