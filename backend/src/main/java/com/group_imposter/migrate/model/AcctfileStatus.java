package com.group_imposter.migrate.model;

import com.group_imposter.migrate.constant.ValueConst;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AcctfileStatus {

  private String acctfileStat1;
  private String acctfileStat2;
   
  public  AcctfileStatus(){
      acctfileStat1 = ValueConst.SPACE;
      acctfileStat2 = ValueConst.SPACE;
  }
}