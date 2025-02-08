package com.group_imposter.migrate.batch.prepare_data;

import com.group_imposter.migrate.model.TranCatBalRecord;
import com.group_imposter.migrate.model.TranRecord;
import org.springframework.batch.item.ItemProcessor;

public class PrepareJobProcessor implements ItemProcessor<TranCatBalRecord, TranCatBalRecord> {
    @Override
    public TranCatBalRecord process(TranCatBalRecord item) throws Exception {
        return item;
    }
}
