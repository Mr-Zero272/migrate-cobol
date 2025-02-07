package com.group_imposter.migrate.repository;

import com.group_imposter.migrate.model.AccountRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRecordRepository extends JpaRepository<AccountRecord, Long> {
}
