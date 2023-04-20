package com.bank.transfer.service;

import com.bank.transfer.aop.DeleteToLog;
import com.bank.transfer.entity.AccountTransferEntity;
import com.bank.transfer.repository.AccountTransferRepository;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
public class AccountTransferServiceImpl implements AccountTransferService {

    private final AccountTransferRepository accountTransferRepository;

    public AccountTransferServiceImpl(AccountTransferRepository accountTransferRepository) {
        this.accountTransferRepository = accountTransferRepository;
    }

    @Override
    public List<AccountTransferEntity> getAll() {
        log.info("try to get All accountTransfersEntities");
        var getAllAccountTransfersEntities = accountTransferRepository.findAll();
        log.info("getAll accountTransfersEntities success, count = {}", getAllAccountTransfersEntities.size());
        return getAllAccountTransfersEntities;
    }

    @Override
    public Optional<AccountTransferEntity> getById(Long id) {
        log.info("try to get accountTransferEntity by id={}", id);
        var getByIdAccountTransferEntity = accountTransferRepository.findById(id);
        log.info("getById accountTransferEntity success, getByIdAccountTransferEntity.isPresent() = {}",
                getByIdAccountTransferEntity.isPresent());
        return getByIdAccountTransferEntity;
    }

    @Override
    public Optional<AccountTransferEntity> getByAccountNumber(Long accountNumber) {
        log.info("try to get accountTransferEntity by accountNumber={}", accountNumber);
        var getByNumberAccountTransferEntity = accountTransferRepository.getByAccountNumber(accountNumber);
        log.info("getByAccountNumber accountTransferEntity success, getByNumberAccountTransferEntity.isPresent() = {}",
                getByNumberAccountTransferEntity.isPresent());
        return getByNumberAccountTransferEntity;
    }

    @Override
    @Transactional
    public void save(AccountTransferEntity accountTransferEntity) {
        log.info("try to save accountTransferEntity: {}", accountTransferEntity);
        accountTransferRepository.save(accountTransferEntity);
        log.info("save accountTransferEntity success, id={}", accountTransferEntity.getId());
    }

    @Override
    @Transactional
    public void update(Long id, AccountTransferEntity accountTransferEntity) {
        accountTransferEntity.setId(id);
        log.info("try to update accountTransferEntity: {}", accountTransferEntity);
        accountTransferRepository.save(accountTransferEntity);
        log.info("update accountTransferEntity success, id={}", accountTransferEntity.getId());
    }

    @Override
    @Transactional
    @Timed("deleteFromService")
    @DeleteToLog
    public void delete(Long id) {
        log.info("try to delete accountTransferEntity with id={}", id);
        accountTransferRepository.deleteById(id);
        log.info("success delete accountTransferEntity with id={}", id);
    }
}