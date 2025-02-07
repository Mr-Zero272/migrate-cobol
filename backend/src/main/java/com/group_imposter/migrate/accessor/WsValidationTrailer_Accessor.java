package com.group_imposter.migrate.accessor;

import com.group_imposter.migrate.constant.ValueConst;
import com.group_imposter.migrate.model.WsValidationTrailer;
import com.group_imposter.migrate.util.FieldFormat;
import com.group_imposter.migrate.util.ValueUtil;

public class WsValidationTrailer_Accessor {

   
  public static String getWsValidationTrailer(WsValidationTrailer wsValidationTrailer){
      StringBuilder sb = new StringBuilder();
      sb.append(FieldFormat.format(4, wsValidationTrailer.getWsValidationFailReason()));
      sb.append(FieldFormat.format(76, wsValidationTrailer.getWsValidationFailReasonDesc()));
      return sb.toString();
  }


  public static void setWsValidationTrailer(WsValidationTrailer wsValidationTrailer, String value){
      value = FieldFormat.format(80, value);
      wsValidationTrailer.setWsValidationFailReason(ValueUtil.toShort(value.substring(0, 4)));
      wsValidationTrailer.setWsValidationFailReasonDesc(value.substring(4, 80));
  }


  public static void initializeWsValidationTrailer(WsValidationTrailer wsValidationTrailer){
      wsValidationTrailer.setWsValidationFailReason((short)0);
      wsValidationTrailer.setWsValidationFailReasonDesc(FieldFormat.format(76, ValueConst.SPACE));
  }
}