package com.group_imposter.migrate.batch.cbact04c;

import com.group_imposter.migrate.model.TranCatBalRecord;
import com.group_imposter.migrate.model.TranCatBalRecordId;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

import java.math.BigDecimal;

public class TranCatBalRecordFieldSetMapper implements FieldSetMapper<TranCatBalRecord> {
    @Override
    public TranCatBalRecord mapFieldSet(FieldSet fieldSet) {
        TranCatBalRecordId recordId = new TranCatBalRecordId();
        recordId.setTrancatAcctId(Long.parseLong(fieldSet.readString("trancatAcctId")));
        recordId.setTrancatTypeCd(fieldSet.readString("trancatTypeCd"));
        recordId.setTrancatCd(Short.parseShort(fieldSet.readString("trancatCd")));

        TranCatBalRecord record = new TranCatBalRecord();
        record.setTranCatBalRecordId(recordId);
        record.setTranCatBal(new BigDecimal(fieldSet.readString("tranCatBal")));
        record.setFiller1(fieldSet.readString("filler1"));

        return record;
    }
}