package com.group_imposter.migrate.batch.prepare_data;

import com.group_imposter.migrate.model.TranCatBalRecord;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

public class PrepareJobWriter implements ItemWriter<TranCatBalRecord> {
    @Override
    public void write(Chunk<? extends TranCatBalRecord> chunk) throws Exception {
        for(TranCatBalRecord record : chunk) {
            System.out.println(record);
        }
    }
}
