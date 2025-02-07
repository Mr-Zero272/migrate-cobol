package com.group_imposter.migrate.accessor;


import com.group_imposter.migrate.constant.ValueConst;
import com.group_imposter.migrate.model.FdAcctfileRec;
import com.group_imposter.migrate.util.FieldFormat;
import com.group_imposter.migrate.util.ValueUtil;

public class FdAcctfileRec_Accessor {

   
  public static String getFdAcctfileRec(FdAcctfileRec fdAcctfileRec){
      StringBuilder sb = new StringBuilder();
      sb.append(FieldFormat.format(11, fdAcctfileRec.getFdAcctId()));
      sb.append(FieldFormat.format(289, fdAcctfileRec.getFdAcctData()));
      return sb.toString();
  }


  public static void setFdAcctfileRec(FdAcctfileRec fdAcctfileRec, String value){
      value = FieldFormat.format(300, value);
      fdAcctfileRec.setFdAcctId(ValueUtil.toLong(value.substring(0, 11)));
      fdAcctfileRec.setFdAcctData(value.substring(11, 300));
  }


  public static void initializeFdAcctfileRec(FdAcctfileRec fdAcctfileRec){
      fdAcctfileRec.setFdAcctId(0);
      fdAcctfileRec.setFdAcctData(FieldFormat.format(289, ValueConst.SPACE));
  }
}