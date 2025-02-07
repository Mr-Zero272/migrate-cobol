package com.group_imposter.migrate.model;

import com.group_imposter.migrate.constant.ValueConst;
import com.group_imposter.migrate.util.FieldFormat;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FdTranCatBalRecord {

    private TranCatBalRecordId fdTranCatBalRecordId;
  private String fdFdTranCatData;
   
  public FdTranCatBalRecord(){
      fdTranCatBalRecordId = new TranCatBalRecordId();
      fdFdTranCatData = FieldFormat.format(33, ValueConst.SPACE);
  }


}