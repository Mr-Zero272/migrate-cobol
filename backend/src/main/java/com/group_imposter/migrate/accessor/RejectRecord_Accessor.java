package com.group_imposter.migrate.accessor;

import com.group_imposter.migrate.constant.ValueConst;
import com.group_imposter.migrate.model.RejectRecord;
import com.group_imposter.migrate.util.FieldFormat;

public class RejectRecord_Accessor {

   
  public static String getRejectRecord(RejectRecord rejectRecord){
      StringBuilder sb = new StringBuilder();
      sb.append(FieldFormat.format(350, rejectRecord.getRejectTranData()));
      sb.append(FieldFormat.format(80, rejectRecord.getValidationTrailer()));
      return sb.toString();
  }


  public static void setRejectRecord(RejectRecord rejectRecord, String value){
      value = FieldFormat.format(430, value);
      rejectRecord.setRejectTranData(value.substring(0, 350));
      rejectRecord.setValidationTrailer(value.substring(350, 430));
  }


  public static void initializeRejectRecord(RejectRecord rejectRecord){
      rejectRecord.setRejectTranData(FieldFormat.format(350, ValueConst.SPACE));
      rejectRecord.setValidationTrailer(FieldFormat.format(80, ValueConst.SPACE));
  }
}