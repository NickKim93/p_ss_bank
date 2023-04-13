package com.bank.transfer.repository;

import com.bank.transfer.entity.CardTransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
/**
 * dao-слой Card Transfer Entity, имплементирующий JpaRepository
 * @author Savenkov Artem
 */
@Repository
public interface CardTransferRepository extends JpaRepository<CardTransferEntity, BigInteger> {
}
