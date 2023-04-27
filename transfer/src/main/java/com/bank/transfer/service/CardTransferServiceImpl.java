package com.bank.transfer.service;

import com.bank.transfer.aop.DeleteToLog;
import com.bank.transfer.entity.CardTransferEntity;
import com.bank.transfer.repository.CardTransferRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service
@Transactional(readOnly = true)
public class CardTransferServiceImpl implements CardTransferService {

    private final CardTransferRepository cardTransferRepository;

    public CardTransferServiceImpl(CardTransferRepository cardTransferRepository) {
        this.cardTransferRepository = cardTransferRepository;
    }

    @Override
    public List<CardTransferEntity> getAll() {
        log.info("try to get All cardTransfersEntities");
        var getAllCardTransfersEntities = cardTransferRepository.findAll();
        log.info("getAll cardTransfersEntities success, count = {}",
                getAllCardTransfersEntities.size());
        return getAllCardTransfersEntities;
    }

    @Override
    public Optional<CardTransferEntity> getById(Long id) {
        log.info("try to get cardTransferEntity by id={}", id);
        var getByIdCardTransferEntity = cardTransferRepository.findById(id);
        log.info("getById cardTransferEntity success, getByIdCardTransferEntity.isPresent() = {}",
                getByIdCardTransferEntity.isPresent());
        return getByIdCardTransferEntity;
    }

    @Override
    public Optional<CardTransferEntity> getByCardNumber(Long cardNumber) {
        log.info("try to get cardTransferEntity by cardNumber={}", cardNumber);
        var getByNumberCardTransferEntity = cardTransferRepository.getByCardNumber(cardNumber);
        log.info("getByNumber cardTransferEntity success, transfer.isPresent() = {}",
                getByNumberCardTransferEntity.isPresent());
        return getByNumberCardTransferEntity;
    }

    @Override
    @Transactional
    public void save(CardTransferEntity cardTransferEntity) {
        log.info("try to save cardTransferEntity: {}", cardTransferEntity);
        cardTransferRepository.save(cardTransferEntity);
        log.info("save cardTransferEntity success, id={}", cardTransferEntity.getId());
    }

    @Override
    @Transactional
    public void update(Long id, CardTransferEntity cardTransferEntity) {
        cardTransferEntity.setId(id);
        log.info("try to update cardTransferEntity: {}", cardTransferEntity);
        cardTransferRepository.save(cardTransferEntity);
        log.info("update cardTransferEntity success, id={}", cardTransferEntity.getId());
    }

    @Override
    @Transactional
    @DeleteToLog
    public void delete(Long id) {
        log.info("try to delete cardTransferEntity with id={}", id);
        cardTransferRepository.deleteById(id);
        log.info("success delete cardTransferEntity with id={}", id);
    }
}
