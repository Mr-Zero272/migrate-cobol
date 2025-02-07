package com.group_imposter.migrate.accessor;

import com.group_imposter.migrate.constant.ValueConst;
import com.group_imposter.migrate.model.AccountRecord;
import com.group_imposter.migrate.util.FieldFormat;
import com.group_imposter.migrate.util.ValueUtil;

import java.math.BigDecimal;

public class AccountRecord_Accessor {  

   
  public static String getAccountRecord(AccountRecord accountRecord){
      StringBuilder sb = new StringBuilder();
      sb.append(FieldFormat.format(11, accountRecord.getAcctId()));
      sb.append(FieldFormat.format(1, accountRecord.getAcctActiveStatus()));
      sb.append(FieldFormat.format(10, 2, true, accountRecord.getAcctCurrBal()).replace(".", ""));
      sb.append(FieldFormat.format(10, 2, true, accountRecord.getAcctCreditLimit()).replace(".", ""));
      sb.append(FieldFormat.format(10, 2, true, accountRecord.getAcctCashCreditLimit()).replace(".", ""));
      sb.append(FieldFormat.format(10, accountRecord.getAcctOpenDate()));
      sb.append(FieldFormat.format(10, accountRecord.getAcctExpiraionDate()));
      sb.append(FieldFormat.format(10, accountRecord.getAcctReissueDate()));
      sb.append(FieldFormat.format(10, 2, true, accountRecord.getAcctCurrCycCredit()).replace(".", ""));
      sb.append(FieldFormat.format(10, 2, true, accountRecord.getAcctCurrCycDebit()).replace(".", ""));
      sb.append(FieldFormat.format(10, accountRecord.getAcctAddrZip()));
      sb.append(FieldFormat.format(10, accountRecord.getAcctGroupId()));
      sb.append(FieldFormat.format(178, accountRecord.getFiller1()));
      return sb.toString();
  }


  public static void setAccountRecord(AccountRecord accountRecord, String value){
      value = FieldFormat.format(300, value);
      accountRecord.setAcctId(ValueUtil.toLong(value.substring(0, 11)));
      accountRecord.setAcctActiveStatus(value.substring(11, 12));
      accountRecord.setAcctCurrBal(ValueUtil.toBigDecimal(10, 2, value.substring(12, 24)));
      accountRecord.setAcctCreditLimit(ValueUtil.toBigDecimal(10, 2, value.substring(24, 36)));
      accountRecord.setAcctCashCreditLimit(ValueUtil.toBigDecimal(10, 2, value.substring(36, 48)));
      accountRecord.setAcctOpenDate(value.substring(48, 58));
      accountRecord.setAcctExpiraionDate(value.substring(58, 68));
      accountRecord.setAcctReissueDate(value.substring(68, 78));
      accountRecord.setAcctCurrCycCredit(ValueUtil.toBigDecimal(10, 2, value.substring(78, 90)));
      accountRecord.setAcctCurrCycDebit(ValueUtil.toBigDecimal(10, 2, value.substring(90, 102)));
      accountRecord.setAcctAddrZip(value.substring(102, 112));
      accountRecord.setAcctGroupId(value.substring(112, 122));
      accountRecord.setFiller1(value.substring(122, 300));
  }


  public static void initializeAccountRecord(AccountRecord accountRecord){
      accountRecord.setAcctId(0);
      accountRecord.setAcctActiveStatus(ValueConst.SPACE);
      accountRecord.setAcctCurrBal(new BigDecimal(0));
      accountRecord.setAcctCreditLimit(new BigDecimal(0));
      accountRecord.setAcctCashCreditLimit(new BigDecimal(0));
      accountRecord.setAcctOpenDate(FieldFormat.format(10, ValueConst.SPACE));
      accountRecord.setAcctExpiraionDate(FieldFormat.format(10, ValueConst.SPACE));
      accountRecord.setAcctReissueDate(FieldFormat.format(10, ValueConst.SPACE));
      accountRecord.setAcctCurrCycCredit(new BigDecimal(0));
      accountRecord.setAcctCurrCycDebit(new BigDecimal(0));
      accountRecord.setAcctAddrZip(FieldFormat.format(10, ValueConst.SPACE));
      accountRecord.setAcctGroupId(FieldFormat.format(10, ValueConst.SPACE));
      accountRecord.setFiller1(FieldFormat.format(178, ValueConst.SPACE));
  }
}