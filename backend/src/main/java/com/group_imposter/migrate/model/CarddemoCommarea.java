package com.group_imposter.migrate.model;

import com.group_imposter.migrate.constant.ValueConst;
import com.group_imposter.migrate.util.FieldFormat;
import lombok.Getter;
import lombok.Setter;

public class CarddemoCommarea {
  @Getter
  @Setter
  private String cdemoFromTranid;
  @Getter
  @Setter
  private String cdemoFromProgram;
  @Getter
  @Setter
  private String cdemoToTranid;
  @Getter
  @Setter
  private String cdemoToProgram;
  @Getter
  @Setter
  private String cdemoUserId;
  @Getter
  @Setter
  private String cdemoUserType;
  @Getter
  @Setter
  private short cdemoPgmContext;
  @Getter
  @Setter
  private int cdemoCustId;
  @Getter
  @Setter
  private String cdemoCustFname;
  @Getter
  @Setter
  private String cdemoCustMname;
  @Getter
  @Setter
  private String cdemoCustLname;
  @Getter
  @Setter
  private long cdemoAcctId;
  @Getter
  @Setter
  private String cdemoAcctStatus;
  @Getter
  @Setter
  private long cdemoCardNum;
  @Getter
  @Setter
  private String cdemoLastMap;
  @Getter
  @Setter
  private String cdemoLastMapset;

  public  CarddemoCommarea(){
    cdemoFromTranid = FieldFormat.format(4, ValueConst.SPACE);
    cdemoFromProgram = FieldFormat.format(8, ValueConst.SPACE);
    cdemoToTranid = FieldFormat.format(4, ValueConst.SPACE);
    cdemoToProgram = FieldFormat.format(8, ValueConst.SPACE);
    cdemoUserId = FieldFormat.format(8, ValueConst.SPACE);
    cdemoUserType = ValueConst.SPACE;
    cdemoPgmContext = (short)0;
    cdemoCustId = 0;
    cdemoCustFname = FieldFormat.format(25, ValueConst.SPACE);
    cdemoCustMname = FieldFormat.format(25, ValueConst.SPACE);
    cdemoCustLname = FieldFormat.format(25, ValueConst.SPACE);
    cdemoAcctId = 0;
    cdemoAcctStatus = ValueConst.SPACE;
    cdemoCardNum = 0;
    cdemoLastMap = FieldFormat.format(7, ValueConst.SPACE);
    cdemoLastMapset = FieldFormat.format(7, ValueConst.SPACE);
  }
}
