package com.group_imposter.migrate.model;

import com.group_imposter.migrate.constant.ValueConst;
import com.group_imposter.migrate.util.FieldFormat;
import lombok.Getter;
import lombok.Setter;

public class CcdaScreenTitle {
  @Getter
  @Setter
  private String ccdaTitle01;
  @Getter
  @Setter
  private String ccdaTitle02;
  @Getter
  @Setter
  private String ccdaThankYou;

  public  CcdaScreenTitle(){
    ccdaTitle01 = FieldFormat.format(40, ValueConst.SPACE);
    ccdaTitle02 = FieldFormat.format(40, ValueConst.SPACE);
    ccdaThankYou = FieldFormat.format(40, ValueConst.SPACE);
  }
}
