package com.group_imposter.migrate.model;

import com.group_imposter.migrate.constant.ValueConst;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TcatbalfStatus {

  private String tcatbalfStat1;
  private String tcatbalfStat2;
   
  public  TcatbalfStatus(){
      tcatbalfStat1 = ValueConst.SPACE;
      tcatbalfStat2 = ValueConst.SPACE;
  }
}