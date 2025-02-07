package com.group_imposter.migrate.accessor;


import com.group_imposter.migrate.constant.ValueConst;
import com.group_imposter.migrate.model.FdXreffileRec;
import com.group_imposter.migrate.util.FieldFormat;

public class FdXreffileRec_Accessor {

   
  public static String getFdXreffileRec(FdXreffileRec fdXreffileRec){
      StringBuilder sb = new StringBuilder();
      sb.append(FieldFormat.format(16, fdXreffileRec.getFdXrefCardNum()));
      sb.append(FieldFormat.format(34, fdXreffileRec.getFdXrefData()));
      return sb.toString();
  }


  public static void setFdXreffileRec(FdXreffileRec fdXreffileRec, String value){
      value = FieldFormat.format(50, value);
      fdXreffileRec.setFdXrefCardNum(value.substring(0, 16));
      fdXreffileRec.setFdXrefData(value.substring(16, 50));
  }


  public static void initializeFdXreffileRec(FdXreffileRec fdXreffileRec){
      fdXreffileRec.setFdXrefCardNum(FieldFormat.format(16, ValueConst.SPACE));
      fdXreffileRec.setFdXrefData(FieldFormat.format(34, ValueConst.SPACE));
  }
}