package com.group_imposter.migrate.model;

import com.group_imposter.migrate.constant.ValueConst;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TranfileStatus {

  private String tranfileStat1;
  private String tranfileStat2;
   
  public  TranfileStatus(){
      tranfileStat1 = ValueConst.SPACE;
      tranfileStat2 = ValueConst.SPACE;
  }
}