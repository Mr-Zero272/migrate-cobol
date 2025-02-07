package com.group_imposter.migrate.accessor;


import com.group_imposter.migrate.constant.ValueConst;
import com.group_imposter.migrate.model.CobolTs;
import com.group_imposter.migrate.util.FieldFormat;

public class CobolTs_Accessor {

   
  public static String getCobolTs(CobolTs cobolTs){
      StringBuilder sb = new StringBuilder();
      sb.append(FieldFormat.format(4, cobolTs.getCobYyyy()));
      sb.append(FieldFormat.format(2, cobolTs.getCobMm()));
      sb.append(FieldFormat.format(2, cobolTs.getCobDd()));
      sb.append(FieldFormat.format(2, cobolTs.getCobHh()));
      sb.append(FieldFormat.format(2, cobolTs.getCobMin()));
      sb.append(FieldFormat.format(2, cobolTs.getCobSs()));
      sb.append(FieldFormat.format(2, cobolTs.getCobMil()));
      sb.append(FieldFormat.format(5, cobolTs.getCobRest()));
      return sb.toString();
  }


  public static void setCobolTs(CobolTs cobolTs, String value){
      value = FieldFormat.format(21, value);
      cobolTs.setCobYyyy(value.substring(0, 4));
      cobolTs.setCobMm(value.substring(4, 6));
      cobolTs.setCobDd(value.substring(6, 8));
      cobolTs.setCobHh(value.substring(8, 10));
      cobolTs.setCobMin(value.substring(10, 12));
      cobolTs.setCobSs(value.substring(12, 14));
      cobolTs.setCobMil(value.substring(14, 16));
      cobolTs.setCobRest(value.substring(16, 21));
  }


  public static void initializeCobolTs(CobolTs cobolTs){
      cobolTs.setCobYyyy(FieldFormat.format(4, ValueConst.SPACE));
      cobolTs.setCobMm(FieldFormat.format(2, ValueConst.SPACE));
      cobolTs.setCobDd(FieldFormat.format(2, ValueConst.SPACE));
      cobolTs.setCobHh(FieldFormat.format(2, ValueConst.SPACE));
      cobolTs.setCobMin(FieldFormat.format(2, ValueConst.SPACE));
      cobolTs.setCobSs(FieldFormat.format(2, ValueConst.SPACE));
      cobolTs.setCobMil(FieldFormat.format(2, ValueConst.SPACE));
      cobolTs.setCobRest(FieldFormat.format(5, ValueConst.SPACE));
  }
}