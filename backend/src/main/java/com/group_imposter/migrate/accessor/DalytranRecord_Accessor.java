package com.group_imposter.migrate.accessor;

import com.group_imposter.migrate.constant.ValueConst;
import com.group_imposter.migrate.model.DalytranRecord;
import com.group_imposter.migrate.util.FieldFormat;
import com.group_imposter.migrate.util.ValueUtil;

import java.math.BigDecimal;

public class DalytranRecord_Accessor {  

   
  public static String getDalytranRecord(DalytranRecord dalytranRecord){
      StringBuilder sb = new StringBuilder();
      sb.append(FieldFormat.format(16, dalytranRecord.getDalytranId()));
      sb.append(FieldFormat.format(2, dalytranRecord.getDalytranTypeCd()));
      sb.append(FieldFormat.format(4, dalytranRecord.getDalytranCatCd()));
      sb.append(FieldFormat.format(10, dalytranRecord.getDalytranSource()));
      sb.append(FieldFormat.format(100, dalytranRecord.getDalytranDesc()));
      sb.append(FieldFormat.format(9, 2, true, dalytranRecord.getDalytranAmt()).replace(".", ""));
      sb.append(FieldFormat.format(9, dalytranRecord.getDalytranMerchantId()));
      sb.append(FieldFormat.format(50, dalytranRecord.getDalytranMerchantName()));
      sb.append(FieldFormat.format(50, dalytranRecord.getDalytranMerchantCity()));
      sb.append(FieldFormat.format(10, dalytranRecord.getDalytranMerchantZip()));
      sb.append(FieldFormat.format(16, dalytranRecord.getDalytranCardNum()));
      sb.append(FieldFormat.format(26, dalytranRecord.getDalytranOrigTs()));
      sb.append(FieldFormat.format(26, dalytranRecord.getDalytranProcTs()));
      sb.append(FieldFormat.format(20, dalytranRecord.getFiller1()));
      return sb.toString();
  }


  public static void setDalytranRecord(DalytranRecord dalytranRecord, String value){
      value = FieldFormat.format(350, value);
      dalytranRecord.setDalytranId(value.substring(0, 16));
      dalytranRecord.setDalytranTypeCd(value.substring(16, 18));
      dalytranRecord.setDalytranCatCd(ValueUtil.toShort(value.substring(18, 22)));
      dalytranRecord.setDalytranSource(value.substring(22, 32));
      dalytranRecord.setDalytranDesc(value.substring(32, 132));
      dalytranRecord.setDalytranAmt(ValueUtil.toBigDecimal(9, 2, value.substring(132, 143)));
      dalytranRecord.setDalytranMerchantId(ValueUtil.toInt(value.substring(143, 152)));
      dalytranRecord.setDalytranMerchantName(value.substring(152, 202));
      dalytranRecord.setDalytranMerchantCity(value.substring(202, 252));
      dalytranRecord.setDalytranMerchantZip(value.substring(252, 262));
      dalytranRecord.setDalytranCardNum(value.substring(262, 278));
      dalytranRecord.setDalytranOrigTs(value.substring(278, 304));
      dalytranRecord.setDalytranProcTs(value.substring(304, 330));
      dalytranRecord.setFiller1(value.substring(330, 350));
  }


  public static void initializeDalytranRecord(DalytranRecord dalytranRecord){
      dalytranRecord.setDalytranId(FieldFormat.format(16, ValueConst.SPACE));
      dalytranRecord.setDalytranTypeCd(FieldFormat.format(2, ValueConst.SPACE));
      dalytranRecord.setDalytranCatCd((short)0);
      dalytranRecord.setDalytranSource(FieldFormat.format(10, ValueConst.SPACE));
      dalytranRecord.setDalytranDesc(FieldFormat.format(100, ValueConst.SPACE));
      dalytranRecord.setDalytranAmt(new BigDecimal(0));
      dalytranRecord.setDalytranMerchantId(0);
      dalytranRecord.setDalytranMerchantName(FieldFormat.format(50, ValueConst.SPACE));
      dalytranRecord.setDalytranMerchantCity(FieldFormat.format(50, ValueConst.SPACE));
      dalytranRecord.setDalytranMerchantZip(FieldFormat.format(10, ValueConst.SPACE));
      dalytranRecord.setDalytranCardNum(FieldFormat.format(16, ValueConst.SPACE));
      dalytranRecord.setDalytranOrigTs(FieldFormat.format(26, ValueConst.SPACE));
      dalytranRecord.setDalytranProcTs(FieldFormat.format(26, ValueConst.SPACE));
      dalytranRecord.setFiller1(FieldFormat.format(20, ValueConst.SPACE));
  }
}