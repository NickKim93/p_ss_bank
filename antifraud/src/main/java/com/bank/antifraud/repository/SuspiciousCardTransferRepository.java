package com.bank.antifraud.repository;

import com.bank.antifraud.entity.SuspiciousCardTransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuspiciousCardTransferRepository extends JpaRepository<SuspiciousCardTransferEntity, Long> {
}
