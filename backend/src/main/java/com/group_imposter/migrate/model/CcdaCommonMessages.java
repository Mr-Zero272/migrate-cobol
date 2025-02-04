package com.group_imposter.migrate.model;

import com.group_imposter.migrate.constant.ValueConst;
import lombok.Getter;
import lombok.Setter;

public class CcdaCommonMessages {
  @Getter
  @Setter
  private String ccdaMsgThankYou;
  @Getter
  @Setter
  private String ccdaMsgInvalidKey;

  public  CcdaCommonMessages(){
    ccdaMsgThankYou = FieldFormat.format(50, ValueConst.SPACE);
    ccdaMsgInvalidKey = FieldFormat.format(50, ValueConst.SPACE);
  }
}
