package com.group_imposter.migrate.model;

import com.group_imposter.migrate.constant.ValueConst;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class IoStatus {

  private String ioStat1;
  private String ioStat2;
   
  public  IoStatus(){
      ioStat1 = ValueConst.SPACE;
      ioStat2 = ValueConst.SPACE;
  }
}