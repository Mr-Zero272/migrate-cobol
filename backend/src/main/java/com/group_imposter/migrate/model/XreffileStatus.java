package com.group_imposter.migrate.model;

import com.group_imposter.migrate.constant.ValueConst;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class XreffileStatus {

  private String xreffileStat1;
  private String xreffileStat2;
   
  public  XreffileStatus(){
      xreffileStat1 = ValueConst.SPACE;
      xreffileStat2 = ValueConst.SPACE;
  }
}