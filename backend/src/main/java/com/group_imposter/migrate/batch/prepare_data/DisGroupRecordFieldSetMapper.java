package com.group_imposter.migrate.batch.prepare_data;

import com.group_imposter.migrate.model.DisGroupKey;
import com.group_imposter.migrate.model.DisGroupRecord;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import java.math.BigDecimal;

public class DisGroupRecordFieldSetMapper implements FieldSetMapper<DisGroupRecord> {

    @Override
    public DisGroupRecord mapFieldSet(FieldSet fieldSet) throws BindException {
        DisGroupKey recordId = new DisGroupKey();
        recordId.setDisAcctGroupId(fieldSet.readString("disAcctGroupId"));
        recordId.setDisTranTypeCd(fieldSet.readString("disTranTypeCd"));
        recordId.setDisTranCatCd(fieldSet.readInt("disTranCatCd"));

        DisGroupRecord record = new DisGroupRecord();
        record.setDisGroupKey(recordId);
        record.setDisIntRate(new BigDecimal(fieldSet.readString("disIntRate")));
        record.setFiller(fieldSet.readString("filler"));

        return record;
    }
}
