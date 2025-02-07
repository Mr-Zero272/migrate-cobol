package com.group_imposter.migrate.accessor;

import com.group_imposter.migrate.constant.ValueConst;
import com.group_imposter.migrate.model.XreffileStatus;
import com.group_imposter.migrate.util.FieldFormat;

public class XreffileStatus_Accessor {

   
  public static String getXreffileStatus(XreffileStatus xreffileStatus){
      StringBuilder sb = new StringBuilder();
      sb.append(FieldFormat.format(1, xreffileStatus.getXreffileStat1()));
      sb.append(FieldFormat.format(1, xreffileStatus.getXreffileStat2()));
      return sb.toString();
  }


  public static void setXreffileStatus(XreffileStatus xreffileStatus, String value){
      value = FieldFormat.format(2, value);
      xreffileStatus.setXreffileStat1(value.substring(0, 1));
      xreffileStatus.setXreffileStat2(value.substring(1, 2));
  }


  public static void initializeXreffileStatus(XreffileStatus xreffileStatus){
      xreffileStatus.setXreffileStat1(ValueConst.SPACE);
      xreffileStatus.setXreffileStat2(ValueConst.SPACE);
  }
}