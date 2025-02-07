package com.group_imposter.migrate.model;


import com.group_imposter.migrate.constant.ValueConst;
import com.group_imposter.migrate.util.FieldFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@Entity
public class AccountRecord {
    @Id
    private long acctId;
    private String acctActiveStatus;
    private BigDecimal acctCurrBal;
    private BigDecimal acctCreditLimit;
    private BigDecimal acctCashCreditLimit;
    private String acctOpenDate;
    private String acctExpiraionDate;
    private String acctReissueDate;
    private BigDecimal acctCurrCycCredit;
    private BigDecimal acctCurrCycDebit;
    private String acctAddrZip;
    private String acctGroupId;
    private String filler1;

    public AccountRecord() {
        acctId = 0;
        acctActiveStatus = ValueConst.SPACE;
        acctCurrBal = new BigDecimal(0);
        acctCreditLimit = new BigDecimal(0);
        acctCashCreditLimit = new BigDecimal(0);
        acctOpenDate = FieldFormat.format(10, ValueConst.SPACE);
        acctExpiraionDate = FieldFormat.format(10, ValueConst.SPACE);
        acctReissueDate = FieldFormat.format(10, ValueConst.SPACE);
        acctCurrCycCredit = new BigDecimal(0);
        acctCurrCycDebit = new BigDecimal(0);
        acctAddrZip = FieldFormat.format(10, ValueConst.SPACE);
        acctGroupId = FieldFormat.format(10, ValueConst.SPACE);
        filler1 = FieldFormat.format(178, ValueConst.SPACE);
    }
}