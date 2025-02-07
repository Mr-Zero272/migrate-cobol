package com.group_imposter.migrate.accessor;

import com.group_imposter.migrate.constant.ValueConst;
import com.group_imposter.migrate.model.AcctfileStatus;
import com.group_imposter.migrate.util.FieldFormat;

public class AcctfileStatus_Accessor {

   
  public static String getAcctfileStatus(AcctfileStatus acctfileStatus){
      StringBuilder sb = new StringBuilder();
      sb.append(FieldFormat.format(1, acctfileStatus.getAcctfileStat1()));
      sb.append(FieldFormat.format(1, acctfileStatus.getAcctfileStat2()));
      return sb.toString();
  }


  public static void setAcctfileStatus(AcctfileStatus acctfileStatus, String value){
      value = FieldFormat.format(2, value);
      acctfileStatus.setAcctfileStat1(value.substring(0, 1));
      acctfileStatus.setAcctfileStat2(value.substring(1, 2));
  }


  public static void initializeAcctfileStatus(AcctfileStatus acctfileStatus){
      acctfileStatus.setAcctfileStat1(ValueConst.SPACE);
      acctfileStatus.setAcctfileStat2(ValueConst.SPACE);
  }
}