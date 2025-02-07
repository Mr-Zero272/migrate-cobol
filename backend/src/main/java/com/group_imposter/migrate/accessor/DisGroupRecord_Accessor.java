package com.group_imposter.migrate.accessor;

import com.group_imposter.migrate.constant.ValueConst;
import com.group_imposter.migrate.model.DisGroupRecord;
import com.group_imposter.migrate.util.FieldFormat;
import com.group_imposter.migrate.util.ValueUtil;

import java.math.BigDecimal;

public class DisGroupRecord_Accessor {

    public static String getDisGroupRecord(DisGroupRecord disGroupRecord){
        StringBuilder sb = new StringBuilder();
        sb.append(FieldFormat.format(10, disGroupRecord.getDisGroupKey().getDisAcctGroupId()));
        sb.append(FieldFormat.format(2, disGroupRecord.getDisGroupKey().getDisTranTypeCd()));
        sb.append(FieldFormat.format(4, disGroupRecord.getDisGroupKey().getDisTranCatCd()));
        sb.append(FieldFormat.format(4, 2, true, disGroupRecord.getDisIntRate()).replace(".", ""));
        sb.append(FieldFormat.format(28, disGroupRecord.getFiller()));
        return sb.toString();
    }


    public static void setDisGroupRecord(DisGroupRecord disGroupRecord, String value){
        value = FieldFormat.format(50, value);
        DisGroupRecord_Accessor.setDisGroupKey(disGroupRecord, value.substring(0, 16));
        disGroupRecord.setDisIntRate(ValueUtil.toBigDecimal(4, 2, value.substring(16, 22)));
        disGroupRecord.setFiller(value.substring(22));
    }


    public static void initializeTranCatBalRecord(DisGroupRecord disGroupRecord){
        DisGroupRecord_Accessor.initializeDisGroupKey(disGroupRecord);
        disGroupRecord.setDisIntRate(new BigDecimal(0));
        disGroupRecord.setFiller(FieldFormat.format(28, ValueConst.SPACE));
    }


    public static String getDisGroupKey(DisGroupRecord disGroupRecord){
        StringBuilder sb = new StringBuilder();
        sb.append(FieldFormat.format(10, disGroupRecord.getDisGroupKey().getDisAcctGroupId()));
        sb.append(FieldFormat.format(2, disGroupRecord.getDisGroupKey().getDisTranTypeCd()));
        sb.append(FieldFormat.format(4, disGroupRecord.getDisGroupKey().getDisTranCatCd()));
        return sb.toString();
    }


    public static void setDisGroupKey(DisGroupRecord disGroupRecord, String value){
        value = FieldFormat.format(16, value);
        disGroupRecord.getDisGroupKey().setDisAcctGroupId(value.substring(0, 10));
        disGroupRecord.getDisGroupKey().setDisTranTypeCd(value.substring(10, 12));
        disGroupRecord.getDisGroupKey().setDisTranCatCd(ValueUtil.toShort(value.substring(12)));
    }


    public static void initializeDisGroupKey(DisGroupRecord disGroupRecord){
        disGroupRecord.getDisGroupKey().setDisAcctGroupId("");
        disGroupRecord.getDisGroupKey().setDisTranTypeCd("");
        disGroupRecord.getDisGroupKey().setDisTranCatCd(0);
    }
}
