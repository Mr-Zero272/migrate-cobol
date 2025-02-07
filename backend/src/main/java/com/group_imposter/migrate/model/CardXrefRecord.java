package com.group_imposter.migrate.model;

import com.group_imposter.migrate.constant.ValueConst;
import com.group_imposter.migrate.util.FieldFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
@Entity
public class CardXrefRecord {

    @Id
    private String xrefCardNum;
    private int xrefCustId;
    private long xrefAcctId;
    private String filler1;

    public CardXrefRecord() {
        xrefCardNum = FieldFormat.format(16, ValueConst.SPACE);
        xrefCustId = 0;
        xrefAcctId = 0;
        filler1 = FieldFormat.format(14, ValueConst.SPACE);
    }

    @Override
    public String toString() {
        return FieldFormat.format(16, xrefCardNum) +
                FieldFormat.format(9, xrefCustId) +
                FieldFormat.format(11, xrefAcctId) +
                FieldFormat.format(14, filler1);
    }
}