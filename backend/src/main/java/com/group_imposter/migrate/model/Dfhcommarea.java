package com.group_imposter.migrate.model;

import com.group_imposter.migrate.constant.ValueConst;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class Dfhcommarea {
  @Getter
  @Setter
  private ArrayList<String> lkCommarea;

  public  Dfhcommarea(){
    this.lkCommarea = new ArrayList<String>();
    for (int i = 0; i < 32767; i++){
      lkCommarea.add(ValueConst.SPACE);
    }
  }
}
