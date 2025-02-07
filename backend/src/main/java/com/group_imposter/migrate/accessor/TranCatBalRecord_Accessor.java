package com.group_imposter.migrate.accessor;

import com.group_imposter.migrate.constant.ValueConst;
import com.group_imposter.migrate.model.TranCatBalRecord;
import com.group_imposter.migrate.util.FieldFormat;
import com.group_imposter.migrate.util.ValueUtil;

import java.math.BigDecimal;

public class TranCatBalRecord_Accessor {

   
  public static String getTranCatBalRecord(TranCatBalRecord tranCatBalRecord){
      StringBuilder sb = new StringBuilder();
      sb.append(FieldFormat.format(11, tranCatBalRecord.getTranCatBalRecordId().getTrancatAcctId()));
      sb.append(FieldFormat.format(2, tranCatBalRecord.getTranCatBalRecordId().getTrancatTypeCd()));
      sb.append(FieldFormat.format(4, tranCatBalRecord.getTranCatBalRecordId().getTrancatCd()));
      sb.append(FieldFormat.format(9, 2, true, tranCatBalRecord.getTranCatBal()).replace(".", ""));
      sb.append(FieldFormat.format(22, tranCatBalRecord.getFiller1()));
      return sb.toString();
  }


  public static void setTranCatBalRecord(TranCatBalRecord tranCatBalRecord, String value){
      value = FieldFormat.format(50, value);
      TranCatBalRecord_Accessor.setTranCatKey(tranCatBalRecord, value.substring(0, 17));
      tranCatBalRecord.setTranCatBal(ValueUtil.toBigDecimal(9, 2, value.substring(17, 28)));
      tranCatBalRecord.setFiller1(value.substring(28, 50));
  }


  public static void initializeTranCatBalRecord(TranCatBalRecord tranCatBalRecord){
      TranCatBalRecord_Accessor.initializeTranCatKey(tranCatBalRecord);
      tranCatBalRecord.setTranCatBal(new BigDecimal(0));
      tranCatBalRecord.setFiller1(FieldFormat.format(22, ValueConst.SPACE));
  }


  public static String getTranCatKey(TranCatBalRecord tranCatBalRecord){
      StringBuilder sb = new StringBuilder();
      sb.append(FieldFormat.format(11, tranCatBalRecord.getTranCatBalRecordId().getTrancatAcctId()));
      sb.append(FieldFormat.format(2, tranCatBalRecord.getTranCatBalRecordId().getTrancatTypeCd()));
      sb.append(FieldFormat.format(4, tranCatBalRecord.getTranCatBalRecordId().getTrancatCd()));
      return sb.toString();
  }


  public static void setTranCatKey(TranCatBalRecord tranCatBalRecord, String value){
      value = FieldFormat.format(17, value);
      tranCatBalRecord.getTranCatBalRecordId().setTrancatAcctId(ValueUtil.toLong(value.substring(0, 11)));
      tranCatBalRecord.getTranCatBalRecordId().setTrancatTypeCd(value.substring(11, 13));
      tranCatBalRecord.getTranCatBalRecordId().setTrancatCd(ValueUtil.toShort(value.substring(13, 17)));
  }


  public static void initializeTranCatKey(TranCatBalRecord tranCatBalRecord){
      tranCatBalRecord.getTranCatBalRecordId().setTrancatAcctId(0);
      tranCatBalRecord.getTranCatBalRecordId().setTrancatTypeCd(FieldFormat.format(2, ValueConst.SPACE));
      tranCatBalRecord.getTranCatBalRecordId().setTrancatCd((short)0);
  }
}