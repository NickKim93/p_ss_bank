package com.bank.transfer.service;
import com.bank.transfer.entity.CardTransferEntity;
import java.util.List;
import java.util.Optional;

public interface CardTransferService {
    List<CardTransferEntity> getAll();
    Optional<CardTransferEntity> getById(Long id);
    Optional<CardTransferEntity> getByCardNumber(Long cardNumber);
    void save(CardTransferEntity cardTransferEntity);
    void update(Long id, CardTransferEntity cardTransferEntity);
    void delete(Long id);
}
