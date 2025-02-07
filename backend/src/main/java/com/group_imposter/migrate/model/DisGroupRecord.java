package com.group_imposter.migrate.model;

import com.group_imposter.migrate.constant.ValueConst;
import com.group_imposter.migrate.util.FieldFormat;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@Builder
public class DisGroupRecord {

    @EmbeddedId
    private DisGroupKey disGroupKey;
    private BigDecimal disIntRate;
    private String filler;

    public DisGroupRecord() {
        disGroupKey = new DisGroupKey();
        disIntRate = BigDecimal.ZERO;
        filler = FieldFormat.format(28, ValueConst.SPACE);
    }
}
