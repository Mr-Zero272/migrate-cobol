package com.group_imposter.migrate.model;

import com.group_imposter.migrate.constant.ValueConst;
import com.group_imposter.migrate.util.FieldFormat;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CobolTs {

  private String cobYyyy;
  private String cobMm;
  private String cobDd;
  private String cobHh;
  private String cobMin;
  private String cobSs;
  private String cobMil;
  private String cobRest;
   
  public  CobolTs(){
      cobYyyy = FieldFormat.format(4, ValueConst.SPACE);
      cobMm = FieldFormat.format(2, ValueConst.SPACE);
      cobDd = FieldFormat.format(2, ValueConst.SPACE);
      cobHh = FieldFormat.format(2, ValueConst.SPACE);
      cobMin = FieldFormat.format(2, ValueConst.SPACE);
      cobSs = FieldFormat.format(2, ValueConst.SPACE);
      cobMil = FieldFormat.format(2, ValueConst.SPACE);
      cobRest = FieldFormat.format(5, ValueConst.SPACE);
  }
}