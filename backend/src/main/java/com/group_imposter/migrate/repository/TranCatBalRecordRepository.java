package com.group_imposter.migrate.repository;

import com.group_imposter.migrate.model.TranCatBalRecord;
import com.group_imposter.migrate.model.TranCatBalRecordId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TranCatBalRecordRepository extends JpaRepository<TranCatBalRecord, TranCatBalRecordId> {
    TranCatBalRecord findByTranCatBalRecordId_TrancatAcctId(long tranCatBalRecordId_trancatAcctId);
}
