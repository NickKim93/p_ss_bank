package com.bank.antifraud.repository;

import com.bank.antifraud.entity.AuditEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Слой доступа к таблице Audit в БД
 * @author Makariy Petrov
 */
@Repository
public interface AuditRepository extends JpaRepository<AuditEntity, Long> {
}
