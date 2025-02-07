package com.group_imposter.migrate.model;

import com.group_imposter.migrate.constant.ValueConst;
import com.group_imposter.migrate.util.FieldFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class TranCatBalRecordId implements Serializable {
    private long trancatAcctId;
    private String trancatTypeCd;
    private short trancatCd;

    public TranCatBalRecordId() {
        trancatAcctId = 0;
        trancatTypeCd = FieldFormat.format(2, ValueConst.SPACE);
        trancatCd = (short) 0;
    }

    public TranCatBalRecordId(String key) {
        trancatAcctId = Long.parseLong(key.substring(0, 11));
        trancatTypeCd = key.substring(11, 13);
        trancatCd = Short.parseShort(key.substring(13, 17));
    }

    @Override
    public String toString() {
        return FieldFormat.format(11, trancatAcctId) +
                FieldFormat.format(2, trancatTypeCd) +
                FieldFormat.format(4, trancatCd);
    }
}
