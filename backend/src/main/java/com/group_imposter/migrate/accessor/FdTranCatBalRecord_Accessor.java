package com.group_imposter.migrate.accessor;


import com.group_imposter.migrate.constant.ValueConst;
import com.group_imposter.migrate.model.FdTranCatBalRecord;
import com.group_imposter.migrate.util.FieldFormat;
import com.group_imposter.migrate.util.ValueUtil;

public class FdTranCatBalRecord_Accessor {

   
  public static String getFdTranCatBalRecord(FdTranCatBalRecord fdTranCatBalRecord){
      StringBuilder sb = new StringBuilder();
      sb.append(FieldFormat.format(11, fdTranCatBalRecord.getFdTranCatBalRecordId().getTrancatAcctId()));
      sb.append(FieldFormat.format(2, fdTranCatBalRecord.getFdTranCatBalRecordId().getTrancatTypeCd()));
      sb.append(FieldFormat.format(4, fdTranCatBalRecord.getFdTranCatBalRecordId().getTrancatCd()));
      sb.append(FieldFormat.format(33, fdTranCatBalRecord.getFdFdTranCatData()));
      return sb.toString();
  }


  public static void setFdTranCatBalRecord(FdTranCatBalRecord fdTranCatBalRecord, String value){
      value = FieldFormat.format(50, value);
      FdTranCatBalRecord_Accessor.setFdTranCatKey(fdTranCatBalRecord, value.substring(0, 17));
      fdTranCatBalRecord.setFdFdTranCatData(value.substring(17, 50));
  }


  public static void initializeFdTranCatBalRecord(FdTranCatBalRecord fdTranCatBalRecord){
      FdTranCatBalRecord_Accessor.initializeFdTranCatKey(fdTranCatBalRecord);
      fdTranCatBalRecord.setFdFdTranCatData(FieldFormat.format(33, ValueConst.SPACE));
  }


  public static String getFdTranCatKey(FdTranCatBalRecord fdTranCatBalRecord){
      StringBuilder sb = new StringBuilder();
      sb.append(FieldFormat.format(11, fdTranCatBalRecord.getFdTranCatBalRecordId().getTrancatAcctId()));
      sb.append(FieldFormat.format(2, fdTranCatBalRecord.getFdTranCatBalRecordId().getTrancatTypeCd()));
      sb.append(FieldFormat.format(4, fdTranCatBalRecord.getFdTranCatBalRecordId().getTrancatCd()));
      return sb.toString();
  }


  public static void setFdTranCatKey(FdTranCatBalRecord fdTranCatBalRecord, String value){
      value = FieldFormat.format(17, value);
      fdTranCatBalRecord.getFdTranCatBalRecordId().setTrancatAcctId(ValueUtil.toLong(value.substring(0, 11)));
      fdTranCatBalRecord.getFdTranCatBalRecordId().setTrancatTypeCd(value.substring(11, 13));
      fdTranCatBalRecord.getFdTranCatBalRecordId().setTrancatCd(ValueUtil.toShort(value.substring(13, 17)));
  }


  public static void initializeFdTranCatKey(FdTranCatBalRecord fdTranCatBalRecord){
      fdTranCatBalRecord.getFdTranCatBalRecordId().setTrancatAcctId(0);
      fdTranCatBalRecord.getFdTranCatBalRecordId().setTrancatTypeCd(FieldFormat.format(2, ValueConst.SPACE));
      fdTranCatBalRecord.getFdTranCatBalRecordId().setTrancatCd((short)0);
  }
}