package com.group_imposter.migrate.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class IoStatus04 {

  private short ioStatus0401;
  private short ioStatus0403;
   
  public  IoStatus04(){
      ioStatus0401 = (short)0;
      ioStatus0403 = (short)0;
  }
}