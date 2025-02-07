package com.group_imposter.migrate.accessor;

import com.group_imposter.migrate.constant.ValueConst;
import com.group_imposter.migrate.model.FdTranRecord;
import com.group_imposter.migrate.util.FieldFormat;

public class FdTranRecord_Accessor {

   
  public static String getFdTranRecord(FdTranRecord fdTranRecord){
      StringBuilder sb = new StringBuilder();
      sb.append(FieldFormat.format(16, fdTranRecord.getFdTranId()));
      sb.append(FieldFormat.format(334, fdTranRecord.getFdCustData()));
      return sb.toString();
  }


  public static void setFdTranRecord(FdTranRecord fdTranRecord, String value){
      value = FieldFormat.format(350, value);
      fdTranRecord.setFdTranId(value.substring(0, 16));
      fdTranRecord.setFdCustData(value.substring(16, 350));
  }


  public static void initializeFdTranRecord(FdTranRecord fdTranRecord){
      fdTranRecord.setFdTranId(FieldFormat.format(16, ValueConst.SPACE));
      fdTranRecord.setFdCustData(FieldFormat.format(334, ValueConst.SPACE));
  }
}