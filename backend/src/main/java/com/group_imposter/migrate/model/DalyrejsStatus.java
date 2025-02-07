package com.group_imposter.migrate.model;

import com.group_imposter.migrate.constant.ValueConst;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DalyrejsStatus {

  private String dalyrejsStat1;
  private String dalyrejsStat2;
   
  public  DalyrejsStatus(){
      dalyrejsStat1 = ValueConst.SPACE;
      dalyrejsStat2 = ValueConst.SPACE;
  }
}