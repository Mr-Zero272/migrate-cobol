package com.group_imposter.migrate.accessor;

import com.group_imposter.migrate.constant.ValueConst;
import com.group_imposter.migrate.model.TcatbalfStatus;
import com.group_imposter.migrate.util.FieldFormat;

public class TcatbalfStatus_Accessor {

   
  public static String getTcatbalfStatus(TcatbalfStatus tcatbalfStatus){
      StringBuilder sb = new StringBuilder();
      sb.append(FieldFormat.format(1, tcatbalfStatus.getTcatbalfStat1()));
      sb.append(FieldFormat.format(1, tcatbalfStatus.getTcatbalfStat2()));
      return sb.toString();
  }


  public static void setTcatbalfStatus(TcatbalfStatus tcatbalfStatus, String value){
      value = FieldFormat.format(2, value);
      tcatbalfStatus.setTcatbalfStat1(value.substring(0, 1));
      tcatbalfStatus.setTcatbalfStat2(value.substring(1, 2));
  }


  public static void initializeTcatbalfStatus(TcatbalfStatus tcatbalfStatus){
      tcatbalfStatus.setTcatbalfStat1(ValueConst.SPACE);
      tcatbalfStatus.setTcatbalfStat2(ValueConst.SPACE);
  }
}