package com.bank.antifraud.repository;

import com.bank.antifraud.entity.CardEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends SuspiciousTransferRepository<CardEntity> {
}
