package com.group_imposter.migrate.model;

import com.group_imposter.migrate.constant.ValueConst;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DalytranStatus {

  private String dalytranStat1;
  private String dalytranStat2;
   
  public  DalytranStatus(){
      dalytranStat1 = ValueConst.SPACE;
      dalytranStat2 = ValueConst.SPACE;
  }
}