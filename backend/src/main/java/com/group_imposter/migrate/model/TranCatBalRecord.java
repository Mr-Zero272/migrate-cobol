package com.group_imposter.migrate.model;

import com.group_imposter.migrate.constant.ValueConst;
import com.group_imposter.migrate.util.FieldFormat;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@Data
@Entity
@AllArgsConstructor
public class TranCatBalRecord implements Serializable {

    @EmbeddedId
    private TranCatBalRecordId tranCatBalRecordId;
    private BigDecimal tranCatBal;
    private String filler1;

    public TranCatBalRecord() {
        tranCatBalRecordId = new TranCatBalRecordId();
        tranCatBal = new BigDecimal(0);
        filler1 = FieldFormat.format(22, ValueConst.SPACE);
    }

    public TranCatBalRecord(TranCatBalRecord tranCatBalRecord) {
        this.tranCatBalRecordId = tranCatBalRecord.getTranCatBalRecordId();
        this.tranCatBal = tranCatBalRecord.getTranCatBal();
        this.filler1 = tranCatBalRecord.getFiller1();
    }

    @Override
    public String toString() {
        return tranCatBalRecordId.toString() + FieldFormat.format(11, String.valueOf(tranCatBal)) + filler1;
    }
}