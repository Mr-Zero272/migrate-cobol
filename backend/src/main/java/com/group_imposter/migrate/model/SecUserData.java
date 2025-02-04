package com.group_imposter.migrate.model;

import com.group_imposter.migrate.constant.ValueConst;
import lombok.Getter;
import lombok.Setter;

public class SecUserData {
  @Getter
  @Setter
  private String secUsrId;
  @Getter
  @Setter
  private String secUsrFname;
  @Getter
  @Setter
  private String secUsrLname;
  @Getter
  @Setter
  private String secUsrPwd;
  @Getter
  @Setter
  private String secUsrType;
  @Getter
  @Setter
  private String secUsrFiller;

  public  SecUserData(){
    secUsrId = FieldFormat.format(8, ValueConst.SPACE);
    secUsrFname = FieldFormat.format(20, ValueConst.SPACE);
    secUsrLname = FieldFormat.format(20, ValueConst.SPACE);
    secUsrPwd = FieldFormat.format(8, ValueConst.SPACE);
    secUsrType = ValueConst.SPACE;
    secUsrFiller = FieldFormat.format(23, ValueConst.SPACE);
  }
}
