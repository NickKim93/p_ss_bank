package com.bank.transfer.repository;

import com.bank.transfer.entity.PhoneTransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
/**
 * dao-слой Phone Transfer Entity, имплементирующий JpaRepository
 * @author Savenkov Artem
 */
@Repository
public interface PhoneTransferRepository extends JpaRepository<PhoneTransferEntity, BigInteger> {
}
