package com.bank.transfer.service;

import com.bank.transfer.aop.DeleteToLog;
import com.bank.transfer.entity.PhoneTransferEntity;
import com.bank.transfer.repository.PhoneTransferRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service
@Transactional(readOnly = true)
public class PhoneTransferServiceImpl implements PhoneTransferService {

    private final PhoneTransferRepository phoneTransferRepository;

    public PhoneTransferServiceImpl(PhoneTransferRepository phoneTransferRepository) {
        this.phoneTransferRepository = phoneTransferRepository;
    }

    @Override
    public List<PhoneTransferEntity> getAll() {
        log.info("try to get All phoneTransfersEntities");
        var getAllPhoneTransfersEntities = phoneTransferRepository.findAll();
        log.info("getAll phoneTransfersEntities success, count = {}", getAllPhoneTransfersEntities.size());
        return getAllPhoneTransfersEntities;
    }

    @Override
    public Optional<PhoneTransferEntity> getById(Long id) {
        log.info("try to get phoneTransferEntity by id={}", id);
        var getByIdPhoneTransferEntity = phoneTransferRepository.findById(id);
        log.info("getById phoneTransferEntity success, getByIdPhoneTransferEntity.isPresent() = {}",
                getByIdPhoneTransferEntity.isPresent());
        return getByIdPhoneTransferEntity;
    }

    @Override
    @Transactional
    public void save(PhoneTransferEntity phoneTransferEntity) {
        log.info("try to save phoneTransferEntity: {}", phoneTransferEntity);
        phoneTransferRepository.save(phoneTransferEntity);
        log.info("save phoneTransferEntity success, id={}", phoneTransferEntity.getId());
    }

    @Override
    @Transactional
    public void update(Long id, PhoneTransferEntity phoneTransferEntity) {
        phoneTransferEntity.setId(id);
        log.info("try to update phoneTransferEntity: {}", phoneTransferEntity);
        phoneTransferRepository.save(phoneTransferEntity);
        log.info("update phoneTransferEntity success, id={}", phoneTransferEntity.getId());
    }

    @Override
    @Transactional
    @DeleteToLog
    public void delete(Long id) {
        log.info("try to delete phoneTransferEntity with id={}", id);
        phoneTransferRepository.deleteById(id);
        log.info("success delete phoneTransferEntity with id={}", id);
    }

    @Override
    public Optional<PhoneTransferEntity> getByPhoneNumber(Long phoneNumber) {
        log.info("try to get phoneTransferEntity by phoneNumber={}", phoneNumber);
        var getByNumberPhoneTransferEntity = phoneTransferRepository.getByPhoneNumber(phoneNumber);
        log.info("getByPhoneNumber phoneTransferEntity success, getByNumberPhoneTransferEntity.isPresent() = {}",
                getByNumberPhoneTransferEntity.isPresent());
        return getByNumberPhoneTransferEntity;
    }
}
