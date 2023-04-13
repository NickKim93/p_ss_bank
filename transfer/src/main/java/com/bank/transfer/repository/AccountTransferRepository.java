package com.bank.transfer.repository;

import com.bank.transfer.entity.AccountTransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

/**
 * dao-слой Account Transfer Entity, имплементирующий JpaRepository
 * @author Savenkov Artem
 */
@Repository
public interface AccountTransferRepository extends JpaRepository<AccountTransferEntity, BigInteger> {
}