package com.group_imposter.migrate.model;

import com.group_imposter.migrate.constant.ValueConst;
import com.group_imposter.migrate.util.FieldFormat;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RejectRecord {

  private String rejectTranData;
  private String validationTrailer;
   
  public  RejectRecord(){
      rejectTranData = FieldFormat.format(350, ValueConst.SPACE);
      validationTrailer = FieldFormat.format(80, ValueConst.SPACE);
  }
}