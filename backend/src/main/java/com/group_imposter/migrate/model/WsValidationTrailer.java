package com.group_imposter.migrate.model;

import com.group_imposter.migrate.constant.ValueConst;
import com.group_imposter.migrate.util.FieldFormat;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WsValidationTrailer {

  private short wsValidationFailReason;
  private String wsValidationFailReasonDesc;
   
  public  WsValidationTrailer(){
      wsValidationFailReason = (short)0;
      wsValidationFailReasonDesc = FieldFormat.format(76, ValueConst.SPACE);
  }
}