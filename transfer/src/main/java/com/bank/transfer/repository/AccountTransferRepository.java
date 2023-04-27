package com.bank.transfer.repository;

import com.bank.transfer.entity.AccountTransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountTransferRepository extends JpaRepository<AccountTransferEntity, Long> {

    Optional<AccountTransferEntity> getByAccountNumber(Long accountNumber);
}