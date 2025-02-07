package com.group_imposter.migrate.model;

import com.group_imposter.migrate.constant.ValueConst;
import com.group_imposter.migrate.util.FieldFormat;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FdTranfileRec {

  private String fdTransId;
  private String fdAcctData;
   
  public  FdTranfileRec(){
      fdTransId = FieldFormat.format(16, ValueConst.SPACE);
      fdAcctData = FieldFormat.format(334, ValueConst.SPACE);
  }
}