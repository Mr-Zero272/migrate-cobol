package com.group_imposter.migrate.accessor;


import com.group_imposter.migrate.constant.ValueConst;
import com.group_imposter.migrate.model.DalytranStatus;
import com.group_imposter.migrate.util.FieldFormat;

public class DalytranStatus_Accessor {

   
  public static String getDalytranStatus(DalytranStatus dalytranStatus){
      StringBuilder sb = new StringBuilder();
      sb.append(FieldFormat.format(1, dalytranStatus.getDalytranStat1()));
      sb.append(FieldFormat.format(1, dalytranStatus.getDalytranStat2()));
      return sb.toString();
  }


  public static void setDalytranStatus(DalytranStatus dalytranStatus, String value){
      value = FieldFormat.format(2, value);
      dalytranStatus.setDalytranStat1(value.substring(0, 1));
      dalytranStatus.setDalytranStat2(value.substring(1, 2));
  }


  public static void initializeDalytranStatus(DalytranStatus dalytranStatus){
      dalytranStatus.setDalytranStat1(ValueConst.SPACE);
      dalytranStatus.setDalytranStat2(ValueConst.SPACE);
  }
}