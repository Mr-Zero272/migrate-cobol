package com.group_imposter.migrate.accessor;

import com.group_imposter.migrate.constant.ValueConst;
import com.group_imposter.migrate.model.TranfileStatus;
import com.group_imposter.migrate.util.FieldFormat;

public class TranfileStatus_Accessor {

   
  public static String getTranfileStatus(TranfileStatus tranfileStatus){
      StringBuilder sb = new StringBuilder();
      sb.append(FieldFormat.format(1, tranfileStatus.getTranfileStat1()));
      sb.append(FieldFormat.format(1, tranfileStatus.getTranfileStat2()));
      return sb.toString();
  }


  public static void setTranfileStatus(TranfileStatus tranfileStatus, String value){
      value = FieldFormat.format(2, value);
      tranfileStatus.setTranfileStat1(value.substring(0, 1));
      tranfileStatus.setTranfileStat2(value.substring(1, 2));
  }


  public static void initializeTranfileStatus(TranfileStatus tranfileStatus){
      tranfileStatus.setTranfileStat1(ValueConst.SPACE);
      tranfileStatus.setTranfileStat2(ValueConst.SPACE);
  }
}