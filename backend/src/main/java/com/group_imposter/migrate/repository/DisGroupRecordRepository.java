package com.group_imposter.migrate.repository;

import com.group_imposter.migrate.model.DisGroupKey;
import com.group_imposter.migrate.model.DisGroupRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisGroupRecordRepository extends JpaRepository<DisGroupRecord, DisGroupKey> {
}
