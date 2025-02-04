package com.group_imposter.migrate.accessor;

import com.group_imposter.migrate.constant.ValueConst;
import com.group_imposter.migrate.model.WsVariables;
import com.group_imposter.migrate.util.FieldFormat;
import com.group_imposter.migrate.util.StringUtil;
import com.group_imposter.migrate.util.ValueUtil;

public class WsVariables_Accessor {

    public static String getWsVariables(WsVariables wsVariables){
        StringBuilder sb = new StringBuilder();
        sb.append(FieldFormat.format(8, wsVariables.getWsPgmname()));
        sb.append(FieldFormat.format(4, wsVariables.getWsTranid()));
        sb.append(FieldFormat.format(80, wsVariables.getWsMessage()));
        sb.append(FieldFormat.format(8, wsVariables.getWsUsrsecFile()));
        sb.append(FieldFormat.format(1, wsVariables.getWsErrFlg()));
        sb.append(FieldFormat.format(9, true, wsVariables.getWsRespCd()));
        sb.append(FieldFormat.format(9, true, wsVariables.getWsReasCd()));
        return sb.toString();
    }


    public static void setWsVariables(WsVariables wsVariables, String value){
        value = FieldFormat.format(119, value);
        wsVariables.setWsPgmname(value.substring(0, 8));
        wsVariables.setWsTranid(value.substring(8, 12));
        wsVariables.setWsMessage(value.substring(12, 92));
        wsVariables.setWsUsrsecFile(value.substring(92, 100));
        wsVariables.setWsErrFlg(value.substring(100, 101));
        wsVariables.setWsRespCd(ValueUtil.toInt(value.substring(101, 110)));
        wsVariables.setWsReasCd(ValueUtil.toInt(value.substring(110, 119)));
    }


    public static void initializeWsVariables(WsVariables wsVariables){
        wsVariables.setWsPgmname(FieldFormat.format(8, ValueConst.SPACE));
        wsVariables.setWsTranid(FieldFormat.format(4, ValueConst.SPACE));
        wsVariables.setWsMessage(FieldFormat.format(80, ValueConst.SPACE));
        wsVariables.setWsUsrsecFile(FieldFormat.format(8, ValueConst.SPACE));
        wsVariables.setWsErrFlg(ValueConst.SPACE);
        wsVariables.setWsRespCd(0);
        wsVariables.setWsReasCd(0);
    }


    public static boolean isErrFlgOn(WsVariables wsVariables){
        String value = wsVariables.getWsErrFlg();
        if (StringUtil.compare(value, "Y") == 0){
            return true;
        }
        return false;
    }


    public static boolean isErrFlgOff(WsVariables wsVariables){
        String value = wsVariables.getWsErrFlg();
        if (StringUtil.compare(value, "N") == 0){
            return true;
        }
        return false;
    }


    public static void setErrFlgOn(WsVariables wsVariables){
        wsVariables.setWsErrFlg("Y");
    }


    public static void setErrFlgOff(WsVariables wsVariables){
        wsVariables.setWsErrFlg("N");
    }
//
}
