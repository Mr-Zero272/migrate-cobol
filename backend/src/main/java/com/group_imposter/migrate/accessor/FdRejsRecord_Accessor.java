package com.group_imposter.migrate.accessor;


import com.group_imposter.migrate.constant.ValueConst;
import com.group_imposter.migrate.model.FdRejsRecord;
import com.group_imposter.migrate.util.FieldFormat;

public class FdRejsRecord_Accessor {

   
  public static String getFdRejsRecord(FdRejsRecord fdRejsRecord){
      StringBuilder sb = new StringBuilder();
      sb.append(FieldFormat.format(350, fdRejsRecord.getFdRejectRecord()));
      sb.append(FieldFormat.format(80, fdRejsRecord.getFdValidationTrailer()));
      return sb.toString();
  }


  public static void setFdRejsRecord(FdRejsRecord fdRejsRecord, String value){
      value = FieldFormat.format(430, value);
      fdRejsRecord.setFdRejectRecord(value.substring(0, 350));
      fdRejsRecord.setFdValidationTrailer(value.substring(350, 430));
  }


  public static void initializeFdRejsRecord(FdRejsRecord fdRejsRecord){
      fdRejsRecord.setFdRejectRecord(FieldFormat.format(350, ValueConst.SPACE));
      fdRejsRecord.setFdValidationTrailer(FieldFormat.format(80, ValueConst.SPACE));
  }
}