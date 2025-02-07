package com.group_imposter.migrate.batch.cbtrn02c;

import com.group_imposter.migrate.model.TranRecord;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

public class CBTRN02CWriter implements ItemWriter<TranRecord> {

    @Override
    public void write(Chunk<? extends TranRecord> chunk) throws Exception {
        // handle if the record empty here
//        for(TranRecord record : chunk) {
//            System.out.println(record);
//        }
    }
}
