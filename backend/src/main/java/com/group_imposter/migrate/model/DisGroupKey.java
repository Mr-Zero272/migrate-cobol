package com.group_imposter.migrate.model;


import com.group_imposter.migrate.util.FieldFormat;
import lombok.Data;

import java.io.Serializable;

@Data
public class DisGroupKey implements Serializable {
    private String disAcctGroupId;
    private String disTranTypeCd;
    private int disTranCatCd;

    public DisGroupKey() {
        disAcctGroupId = FieldFormat.format(10, 0);
        disTranTypeCd = FieldFormat.format(2, " ");
        disTranCatCd = 0;
    }

    @Override
    public String toString() {
        return disAcctGroupId + disTranTypeCd + FieldFormat.format(4, disTranCatCd);
    }
}
