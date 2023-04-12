package com.bank.antifraud.service;

import com.bank.antifraud.dto.SuspiciousCardTransferDto;
import com.bank.antifraud.entity.SuspiciousCardTransferEntity;
import com.bank.antifraud.mapper.SuspiciousCardTransferMapper;
import com.bank.antifraud.repository.SuspiciousCardTransferRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SuspiciousCardTransferServiceImpl implements SuspiciousCardTransferService{
    private static final Logger LOGGER = LoggerFactory.getLogger(SuspiciousCardTransferServiceImpl.class);
    private final SuspiciousCardTransferRepository suspiciousCardTransferRepository;

    public SuspiciousCardTransferServiceImpl(SuspiciousCardTransferRepository suspiciousCardTransferRepository) {
        this.suspiciousCardTransferRepository = suspiciousCardTransferRepository;
    }

    @Override
    @Transactional(readOnly = false)
    public SuspiciousCardTransferEntity save(SuspiciousCardTransferDto suspiciousCardTransferDto) {
        LOGGER.info("В SuspiciousCardTransferServiceImpl сработал метод save");

        SuspiciousCardTransferEntity suspiciousCardTransferEntity = SuspiciousCardTransferMapper
                .INSTANCE.suspiciousCardTransferDtoToSuspiciousCardTransferEntity(suspiciousCardTransferDto);

        return suspiciousCardTransferRepository.save(suspiciousCardTransferEntity);
    }

    @Override
    public SuspiciousCardTransferEntity findById(BigInteger id) {
        LOGGER.info("В SuspiciousCardTransferServiceImpl сработал метод findById: " + id.toString());

        Optional<SuspiciousCardTransferEntity> suspiciousCardTransferEntity = suspiciousCardTransferRepository.findById(id);

        if (suspiciousCardTransferEntity.isPresent()) {
            return suspiciousCardTransferEntity.get();
        } else {
            LOGGER.error("Сущность не найдена");
            throw new EntityNotFoundException("сущность SuspiciousCardTransfer с id: " + id + " не найдена.");
        }
    }

    @Override
    public List<SuspiciousCardTransferEntity> findAll() {
        LOGGER.info("В SuspiciousCardTransferServiceImpl сработал метод findAll");

        return suspiciousCardTransferRepository.findAll();
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(BigInteger id) {
        LOGGER.info("В SuspiciousCardTransferServiceImpl сработал метод delete: " + id.toString());

        SuspiciousCardTransferEntity suspiciousCardTransferEntity = suspiciousCardTransferRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.error("Сущность не найдена");
                    return new EntityNotFoundException("сущность SuspiciousCardTransfer с id: " + id + " не найдена.");
                });

        suspiciousCardTransferRepository.delete(suspiciousCardTransferEntity);
    }

    @Override
    @Transactional(readOnly = false)
    public SuspiciousCardTransferEntity update(SuspiciousCardTransferDto suspiciousCardTransferDto) {
        LOGGER.info("В SuspiciousCardTransferServiceImpl сработал метод update: " + suspiciousCardTransferDto.toString());

        SuspiciousCardTransferEntity updateEntity = suspiciousCardTransferRepository.findById(suspiciousCardTransferDto.id())
                .orElseThrow(() -> {
                    LOGGER.error("Сущность не найдена");
                    return new EntityNotFoundException(suspiciousCardTransferDto.id().toString());
                });

        BeanUtils.copyProperties(suspiciousCardTransferDto, updateEntity);
        return suspiciousCardTransferRepository.save(updateEntity);
    }
}
