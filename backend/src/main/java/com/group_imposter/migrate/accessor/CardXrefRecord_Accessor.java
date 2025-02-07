package com.group_imposter.migrate.accessor;

import com.group_imposter.migrate.constant.ValueConst;
import com.group_imposter.migrate.model.CardXrefRecord;
import com.group_imposter.migrate.util.FieldFormat;
import com.group_imposter.migrate.util.ValueUtil;

public class CardXrefRecord_Accessor {

   
  public static String getCardXrefRecord(CardXrefRecord cardXrefRecord){
      StringBuilder sb = new StringBuilder();
      sb.append(FieldFormat.format(16, cardXrefRecord.getXrefCardNum()));
      sb.append(FieldFormat.format(9, cardXrefRecord.getXrefCustId()));
      sb.append(FieldFormat.format(11, cardXrefRecord.getXrefAcctId()));
      sb.append(FieldFormat.format(14, cardXrefRecord.getFiller1()));
      return sb.toString();
  }


  public static void setCardXrefRecord(CardXrefRecord cardXrefRecord, String value){
      value = FieldFormat.format(50, value);
      cardXrefRecord.setXrefCardNum(value.substring(0, 16));
      cardXrefRecord.setXrefCustId(ValueUtil.toInt(value.substring(16, 25)));
      cardXrefRecord.setXrefAcctId(ValueUtil.toLong(value.substring(25, 36)));
      cardXrefRecord.setFiller1(value.substring(36, 50));
  }


  public static void initializeCardXrefRecord(CardXrefRecord cardXrefRecord){
      cardXrefRecord.setXrefCardNum(FieldFormat.format(16, ValueConst.SPACE));
      cardXrefRecord.setXrefCustId(0);
      cardXrefRecord.setXrefAcctId(0);
      cardXrefRecord.setFiller1(FieldFormat.format(14, ValueConst.SPACE));
  }
}