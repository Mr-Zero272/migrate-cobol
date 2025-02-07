package com.group_imposter.migrate.batch.cbact04c;

import com.group_imposter.migrate.model.TranRecord;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

public class CBACT04Writer implements ItemWriter<TranRecord> {
    @Override
    public void write(Chunk<? extends TranRecord> chunk) throws Exception {
        for(TranRecord record : chunk) {
            System.out.println(record);
        }
    }
}
