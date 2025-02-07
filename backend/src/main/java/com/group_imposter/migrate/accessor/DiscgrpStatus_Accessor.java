package com.group_imposter.migrate.accessor;

import com.group_imposter.migrate.constant.ValueConst;
import com.group_imposter.migrate.model.DiscgrpStatus;
import com.group_imposter.migrate.util.FieldFormat;

public class DiscgrpStatus_Accessor {
    public static String getDiscgrpStatus(DiscgrpStatus discgrpStatus){
        StringBuilder sb = new StringBuilder();
        sb.append(FieldFormat.format(1, discgrpStatus.getDiscgrpStat1()));
        sb.append(FieldFormat.format(1, discgrpStatus.getDiscgrpStat2()));
        return sb.toString();
    }


    public static void setDiscgrpStatus(DiscgrpStatus discgrpStatus, String value){
        value = FieldFormat.format(2, value);
        discgrpStatus.setDiscgrpStat1(value.substring(0, 1));
        discgrpStatus.setDiscgrpStat2(value.substring(1, 2));
    }


    public static void initializeDiscgrpStatus(DiscgrpStatus discgrpStatus){
        discgrpStatus.setDiscgrpStat1(ValueConst.SPACE);
        discgrpStatus.setDiscgrpStat2(ValueConst.SPACE);
    }
}
