package com.group_imposter.migrate.accessor;

import com.group_imposter.migrate.constant.ValueConst;
import com.group_imposter.migrate.model.IoStatus;
import com.group_imposter.migrate.util.FieldFormat;

public class IoStatus_Accessor {

   
  public static String getIoStatus(IoStatus ioStatus){
      StringBuilder sb = new StringBuilder();
      sb.append(FieldFormat.format(1, ioStatus.getIoStat1()));
      sb.append(FieldFormat.format(1, ioStatus.getIoStat2()));
      return sb.toString();
  }


  public static void setIoStatus(IoStatus ioStatus, String value){
      value = FieldFormat.format(2, value);
      ioStatus.setIoStat1(value.substring(0, 1));
      ioStatus.setIoStat2(value.substring(1, 2));
  }


  public static void initializeIoStatus(IoStatus ioStatus){
      ioStatus.setIoStat1(ValueConst.SPACE);
      ioStatus.setIoStat2(ValueConst.SPACE);
  }
}