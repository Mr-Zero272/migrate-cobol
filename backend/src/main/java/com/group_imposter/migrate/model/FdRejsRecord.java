package com.group_imposter.migrate.model;

import com.group_imposter.migrate.constant.ValueConst;
import com.group_imposter.migrate.util.FieldFormat;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FdRejsRecord {

  private String fdRejectRecord;
  private String fdValidationTrailer;
   
  public  FdRejsRecord(){
      fdRejectRecord = FieldFormat.format(350, ValueConst.SPACE);
      fdValidationTrailer = FieldFormat.format(80, ValueConst.SPACE);
  }
}