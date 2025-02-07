package com.group_imposter.migrate.accessor;

import com.group_imposter.migrate.constant.ValueConst;
import com.group_imposter.migrate.model.DalyrejsStatus;
import com.group_imposter.migrate.util.FieldFormat;

public class DalyrejsStatus_Accessor {

   
  public static String getDalyrejsStatus(DalyrejsStatus dalyrejsStatus){
      StringBuilder sb = new StringBuilder();
      sb.append(FieldFormat.format(1, dalyrejsStatus.getDalyrejsStat1()));
      sb.append(FieldFormat.format(1, dalyrejsStatus.getDalyrejsStat2()));
      return sb.toString();
  }


  public static void setDalyrejsStatus(DalyrejsStatus dalyrejsStatus, String value){
      value = FieldFormat.format(2, value);
      dalyrejsStatus.setDalyrejsStat1(value.substring(0, 1));
      dalyrejsStatus.setDalyrejsStat2(value.substring(1, 2));
  }


  public static void initializeDalyrejsStatus(DalyrejsStatus dalyrejsStatus){
      dalyrejsStatus.setDalyrejsStat1(ValueConst.SPACE);
      dalyrejsStatus.setDalyrejsStat2(ValueConst.SPACE);
  }
}