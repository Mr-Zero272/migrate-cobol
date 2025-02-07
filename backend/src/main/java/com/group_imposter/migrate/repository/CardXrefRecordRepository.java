package com.group_imposter.migrate.repository;

import com.group_imposter.migrate.model.CardXrefRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardXrefRecordRepository extends JpaRepository<CardXrefRecord, String> {
    Optional<CardXrefRecord> findByXrefAcctId(long xrefAcctId);
}
