package com.group_imposter.migrate.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class WsMiscVars {
    private long wsLastAcctNum;
    private BigDecimal wsMonthlyInt;
    private BigDecimal wsTotalInt;
    private char firstTime = 'Y';
}
