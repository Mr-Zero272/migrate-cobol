package com.group_imposter.migrate.accessor;

import com.group_imposter.migrate.constant.ValueConst;
import com.group_imposter.migrate.model.FdTranfileRec;
import com.group_imposter.migrate.util.FieldFormat;

public class FdTranfileRec_Accessor {

   
  public static String getFdTranfileRec(FdTranfileRec fdTranfileRec){
      StringBuilder sb = new StringBuilder();
      sb.append(FieldFormat.format(16, fdTranfileRec.getFdTransId()));
      sb.append(FieldFormat.format(334, fdTranfileRec.getFdAcctData()));
      return sb.toString();
  }


  public static void setFdTranfileRec(FdTranfileRec fdTranfileRec, String value){
      value = FieldFormat.format(350, value);
      fdTranfileRec.setFdTransId(value.substring(0, 16));
      fdTranfileRec.setFdAcctData(value.substring(16, 350));
  }


  public static void initializeFdTranfileRec(FdTranfileRec fdTranfileRec){
      fdTranfileRec.setFdTransId(FieldFormat.format(16, ValueConst.SPACE));
      fdTranfileRec.setFdAcctData(FieldFormat.format(334, ValueConst.SPACE));
  }
}