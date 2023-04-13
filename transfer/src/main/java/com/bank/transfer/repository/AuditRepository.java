package com.bank.transfer.repository;

import com.bank.transfer.entity.AuditEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
/**
 * dao-слой Audit Entity, имплементирующий JpaRepository
 * @author Savenkov Artem
 */
@Repository
public interface AuditRepository extends JpaRepository<AuditEntity, BigInteger> {
}
