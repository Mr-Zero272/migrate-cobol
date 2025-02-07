package com.group_imposter.migrate.accessor;

import com.group_imposter.migrate.model.IoStatus04;
import com.group_imposter.migrate.util.FieldFormat;
import com.group_imposter.migrate.util.ValueUtil;

public class IoStatus04_Accessor {

   
  public static String getIoStatus04(IoStatus04 ioStatus04){
      StringBuilder sb = new StringBuilder();
      sb.append(FieldFormat.format(1, ioStatus04.getIoStatus0401()));
      sb.append(FieldFormat.format(3, ioStatus04.getIoStatus0403()));
      return sb.toString();
  }


  public static void setIoStatus04(IoStatus04 ioStatus04, String value){
      value = FieldFormat.format(4, value);
      ioStatus04.setIoStatus0401(ValueUtil.toShort(value.substring(0, 1)));
      ioStatus04.setIoStatus0403(ValueUtil.toShort(value.substring(1, 4)));
  }


  public static void initializeIoStatus04(IoStatus04 ioStatus04){
      ioStatus04.setIoStatus0401((short)0);
      ioStatus04.setIoStatus0403((short)0);
  }
}