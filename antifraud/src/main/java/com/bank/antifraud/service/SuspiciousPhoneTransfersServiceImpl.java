package com.bank.antifraud.service;

import com.bank.antifraud.audit.Auditing;
import com.bank.antifraud.dto.SuspiciousPhoneTransfersDto;
import com.bank.antifraud.entity.SuspiciousPhoneTransfersEntity;
import com.bank.antifraud.mapper.SuspiciousPhoneTransfersMapper;
import com.bank.antifraud.repository.SuspiciousPhoneTransfersRepository;
import com.bank.antifraud.util.OperationType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SuspiciousPhoneTransfersServiceImpl implements SuspiciousPhoneTransfersService{
    private static final Logger LOGGER = LoggerFactory.getLogger(SuspiciousPhoneTransfersServiceImpl.class);
    private final SuspiciousPhoneTransfersRepository suspiciousPhoneTransfersRepository;

    public SuspiciousPhoneTransfersServiceImpl(SuspiciousPhoneTransfersRepository suspiciousPhoneTransfersRepository) {
        this.suspiciousPhoneTransfersRepository = suspiciousPhoneTransfersRepository;
    }

    @Override
    @Transactional
    @Auditing(operationType = OperationType.CREATE)
    public SuspiciousPhoneTransfersEntity save(SuspiciousPhoneTransfersDto suspiciousPhoneTransfersDto) {
        LOGGER.info("В SuspiciousPhoneTransfersServiceImpl вызван метод save");

        SuspiciousPhoneTransfersEntity suspiciousPhoneTransfersEntity = SuspiciousPhoneTransfersMapper.INSTANCE
                .suspiciousPhoneTransfersDtoToSuspiciousPhoneTransfersEntity(suspiciousPhoneTransfersDto);

        return suspiciousPhoneTransfersRepository.save(suspiciousPhoneTransfersEntity);
    }

    @Override
    public SuspiciousPhoneTransfersEntity findById(Long id) {
        LOGGER.info("В SuspiciousPhoneTransfersServiceImpl вызван метод findById: " + id.toString());

        Optional<SuspiciousPhoneTransfersEntity> suspiciousPhoneTransfersEntity = suspiciousPhoneTransfersRepository.findById(id);

        if (suspiciousPhoneTransfersEntity.isPresent()) {
            return suspiciousPhoneTransfersEntity.get();
        } else {
            LOGGER.error("Сущность не найдена");
            throw new EntityNotFoundException("сущность suspiciousPhoneTransfers с id: " + id + " не найдена.");
        }
    }

    @Override
    public List<SuspiciousPhoneTransfersEntity> findAll() {
        LOGGER.info("В SuspiciousPhoneTransfersServiceImpl вызван метод findAll");

        return suspiciousPhoneTransfersRepository.findAll();
    }

    @Override
    @Transactional()
    public void delete(Long id) {
        LOGGER.info("В SuspiciousPhoneTransfersServiceImpl вызван метод delete: " + id.toString());

        SuspiciousPhoneTransfersEntity suspiciousPhoneTransfersEntity = suspiciousPhoneTransfersRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.error("Сущность не найдена");
                    return new EntityNotFoundException("сущность SuspiciousPhoneTransfers с id: " + id + " не найдена.");
                });

        suspiciousPhoneTransfersRepository.delete(suspiciousPhoneTransfersEntity);
    }

    @Override
    @Transactional
    @Auditing(operationType = OperationType.UPDATE)
    public SuspiciousPhoneTransfersEntity update(SuspiciousPhoneTransfersDto suspiciousPhoneTransfersDto) {
        LOGGER.info("В SuspiciousPhoneTransfersServiceImpl вызван метод update: " + suspiciousPhoneTransfersDto.toString());

        SuspiciousPhoneTransfersEntity updateEntity = suspiciousPhoneTransfersRepository.findById(suspiciousPhoneTransfersDto.id())
                .orElseThrow(() -> {
                    LOGGER.error("Сущность не найдена");
                    return new EntityNotFoundException(suspiciousPhoneTransfersDto.id().toString());
                });

        BeanUtils.copyProperties(suspiciousPhoneTransfersDto, updateEntity);
        return suspiciousPhoneTransfersRepository.save(updateEntity);
    }
}