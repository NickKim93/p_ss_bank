package com.bank.publicinfo.repository;

import com.bank.publicinfo.entity.Audit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface AuditRepository extends JpaRepository<Audit, BigInteger> {
}
