package com.bank.transfer.repository;

import com.bank.transfer.entity.CardTransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardTransferRepository extends JpaRepository<CardTransferEntity, Long> {
    Optional<CardTransferEntity> getByCardNumber(Long cardNumber);
}
