package com.group_imposter.migrate.repository;

import com.group_imposter.migrate.model.TranRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranRecordRepository extends JpaRepository<TranRecord, String> {
}
