package com.group_imposter.migrate.accessor;

import com.group_imposter.migrate.model.WsCounters;
import com.group_imposter.migrate.util.FieldFormat;
import com.group_imposter.migrate.util.ValueUtil;

import java.math.BigDecimal;

public class WsCounters_Accessor {

   
  public static String getWsCounters(WsCounters wsCounters){
      StringBuilder sb = new StringBuilder();
      sb.append(FieldFormat.format(9, wsCounters.getWsTransactionCount()));
      sb.append(FieldFormat.format(9, wsCounters.getWsRejectCount()));
      sb.append(FieldFormat.format(9, 2, true, wsCounters.getWsTempBal()).replace(".", ""));
      return sb.toString();
  }


  public static void setWsCounters(WsCounters wsCounters, String value){
      value = FieldFormat.format(29, value);
      wsCounters.setWsTransactionCount(ValueUtil.toInt(value.substring(0, 9)));
      wsCounters.setWsRejectCount(ValueUtil.toInt(value.substring(9, 18)));
      wsCounters.setWsTempBal(ValueUtil.toBigDecimal(9, 2, value.substring(18, 29)));
  }


  public static void initializeWsCounters(WsCounters wsCounters){
      wsCounters.setWsTransactionCount(0);
      wsCounters.setWsRejectCount(0);
      wsCounters.setWsTempBal(new BigDecimal(0));
  }
}