package com.group_imposter.migrate.model;

import com.group_imposter.migrate.constant.ValueConst;
import com.group_imposter.migrate.util.FieldFormat;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FdAcctfileRec {

  private long fdAcctId;
  private String fdAcctData;
   
  public  FdAcctfileRec(){
      fdAcctId = 0;
      fdAcctData = FieldFormat.format(289, ValueConst.SPACE);
  }
}