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
public class TranRecord {

    @Id
    private String tranId;
    private String tranTypeCd;
    private short tranCatCd;
    private String tranSource;
    private String tranDesc;
    private BigDecimal tranAmt;
    private int tranMerchantId;
    private String tranMerchantName;
    private String tranMerchantCity;
    private String tranMerchantZip;
    private String tranCardNum;
    private String tranOrigTs;
    private String tranProcTs;
    private String filler1;

    public TranRecord() {
        tranId = FieldFormat.format(16, ValueConst.SPACE);
        tranTypeCd = FieldFormat.format(2, ValueConst.SPACE);
        tranCatCd = (short) 0;
        tranSource = FieldFormat.format(10, ValueConst.SPACE);
        tranDesc = FieldFormat.format(100, ValueConst.SPACE);
        tranAmt = new BigDecimal(0);
        tranMerchantId = 0;
        tranMerchantName = FieldFormat.format(50, ValueConst.SPACE);
        tranMerchantCity = FieldFormat.format(50, ValueConst.SPACE);
        tranMerchantZip = FieldFormat.format(10, ValueConst.SPACE);
        tranCardNum = FieldFormat.format(16, ValueConst.SPACE);
        tranOrigTs = FieldFormat.format(26, ValueConst.SPACE);
        tranProcTs = FieldFormat.format(26, ValueConst.SPACE);
        filler1 = FieldFormat.format(20, ValueConst.SPACE);
    }
}