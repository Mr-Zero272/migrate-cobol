package com.group_imposter.migrate.accessor;

import com.group_imposter.migrate.constant.ValueConst;
import com.group_imposter.migrate.model.TranRecord;
import com.group_imposter.migrate.util.FieldFormat;
import com.group_imposter.migrate.util.ValueUtil;

import java.math.BigDecimal;

public class TranRecord_Accessor {

   
  public static String getTranRecord(TranRecord tranRecord){
      StringBuilder sb = new StringBuilder();
      sb.append(FieldFormat.format(16, tranRecord.getTranId()));
      sb.append(FieldFormat.format(2, tranRecord.getTranTypeCd()));
      sb.append(FieldFormat.format(4, tranRecord.getTranCatCd()));
      sb.append(FieldFormat.format(10, tranRecord.getTranSource()));
      sb.append(FieldFormat.format(100, tranRecord.getTranDesc()));
      sb.append(FieldFormat.format(9, 2, true, tranRecord.getTranAmt()).replace(".", ""));
      sb.append(FieldFormat.format(9, tranRecord.getTranMerchantId()));
      sb.append(FieldFormat.format(50, tranRecord.getTranMerchantName()));
      sb.append(FieldFormat.format(50, tranRecord.getTranMerchantCity()));
      sb.append(FieldFormat.format(10, tranRecord.getTranMerchantZip()));
      sb.append(FieldFormat.format(16, tranRecord.getTranCardNum()));
      sb.append(FieldFormat.format(26, tranRecord.getTranOrigTs()));
      sb.append(FieldFormat.format(26, tranRecord.getTranProcTs()));
      sb.append(FieldFormat.format(20, tranRecord.getFiller1()));
      return sb.toString();
  }


  public static void setTranRecord(TranRecord tranRecord, String value){
      value = FieldFormat.format(350, value);
      tranRecord.setTranId(value.substring(0, 16));
      tranRecord.setTranTypeCd(value.substring(16, 18));
      tranRecord.setTranCatCd(ValueUtil.toShort(value.substring(18, 22)));
      tranRecord.setTranSource(value.substring(22, 32));
      tranRecord.setTranDesc(value.substring(32, 132));
      tranRecord.setTranAmt(ValueUtil.toBigDecimal(9, 2, value.substring(132, 143)));
      tranRecord.setTranMerchantId(ValueUtil.toInt(value.substring(143, 152)));
      tranRecord.setTranMerchantName(value.substring(152, 202));
      tranRecord.setTranMerchantCity(value.substring(202, 252));
      tranRecord.setTranMerchantZip(value.substring(252, 262));
      tranRecord.setTranCardNum(value.substring(262, 278));
      tranRecord.setTranOrigTs(value.substring(278, 304));
      tranRecord.setTranProcTs(value.substring(304, 330));
      tranRecord.setFiller1(value.substring(330, 350));
  }


  public static void initializeTranRecord(TranRecord tranRecord){
      tranRecord.setTranId(FieldFormat.format(16, ValueConst.SPACE));
      tranRecord.setTranTypeCd(FieldFormat.format(2, ValueConst.SPACE));
      tranRecord.setTranCatCd((short)0);
      tranRecord.setTranSource(FieldFormat.format(10, ValueConst.SPACE));
      tranRecord.setTranDesc(FieldFormat.format(100, ValueConst.SPACE));
      tranRecord.setTranAmt(new BigDecimal(0));
      tranRecord.setTranMerchantId(0);
      tranRecord.setTranMerchantName(FieldFormat.format(50, ValueConst.SPACE));
      tranRecord.setTranMerchantCity(FieldFormat.format(50, ValueConst.SPACE));
      tranRecord.setTranMerchantZip(FieldFormat.format(10, ValueConst.SPACE));
      tranRecord.setTranCardNum(FieldFormat.format(16, ValueConst.SPACE));
      tranRecord.setTranOrigTs(FieldFormat.format(26, ValueConst.SPACE));
      tranRecord.setTranProcTs(FieldFormat.format(26, ValueConst.SPACE));
      tranRecord.setFiller1(FieldFormat.format(20, ValueConst.SPACE));
  }
}