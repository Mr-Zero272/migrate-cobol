package com.group_imposter.migrate.model;

import com.group_imposter.migrate.constant.ValueConst;
import com.group_imposter.migrate.util.FieldFormat;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FdXreffileRec {

  private String fdXrefCardNum;
  private String fdXrefData;
   
  public  FdXreffileRec(){
      fdXrefCardNum = FieldFormat.format(16, ValueConst.SPACE);
      fdXrefData = FieldFormat.format(34, ValueConst.SPACE);
  }
}