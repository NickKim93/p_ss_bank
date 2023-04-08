package com.bank.antifraud.repository;

import com.bank.antifraud.entity.SuspiciousAccountTransfersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface SuspiciousAccountTransfersRepository extends JpaRepository<SuspiciousAccountTransfersEntity, BigInteger> {
}
