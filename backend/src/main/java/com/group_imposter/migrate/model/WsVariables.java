package com.group_imposter.migrate.model;

import com.group_imposter.migrate.constant.ValueConst;
import lombok.Getter;
import lombok.Setter;

public class WsVariables {
  @Getter
  @Setter
  private String wsPgmname;
  @Getter
  @Setter
  private String wsTranid;
  @Getter
  @Setter
  private String wsMessage;
  @Getter
  @Setter
  private String wsUsrsecFile;
  @Getter
  @Setter
  private String wsErrFlg;
  @Getter
  @Setter
  private int wsRespCd;
  @Getter
  @Setter
  private int wsReasCd;

  public  WsVariables(){
    wsPgmname = FieldFormat.format(8, ValueConst.SPACE);
    wsTranid = FieldFormat.format(4, ValueConst.SPACE);
    wsMessage = FieldFormat.format(80, ValueConst.SPACE);
    wsUsrsecFile = FieldFormat.format(8, ValueConst.SPACE);
    wsErrFlg = ValueConst.SPACE;
    wsRespCd = 0;
    wsReasCd = 0;
  }
}
