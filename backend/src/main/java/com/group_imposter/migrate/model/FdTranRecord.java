package com.group_imposter.migrate.model;

import com.group_imposter.migrate.constant.ValueConst;
import com.group_imposter.migrate.util.FieldFormat;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FdTranRecord {

  private String fdTranId;
  private String fdCustData;
   
  public  FdTranRecord(){
      fdTranId = FieldFormat.format(16, ValueConst.SPACE);
      fdCustData = FieldFormat.format(334, ValueConst.SPACE);
  }
}