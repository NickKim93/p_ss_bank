package com.bank.antifraud.service;

import com.bank.antifraud.dto.SuspiciousAccountTransfersDto;
import com.bank.antifraud.entity.AuditEntity;
import com.bank.antifraud.entity.SuspiciousAccountTransfersEntity;
import com.bank.antifraud.mapper.AuditMapper;
import com.bank.antifraud.mapper.SuspiciousAccountTransfersMapper;
import com.bank.antifraud.repository.SuspiciousAccountTransfersRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SuspiciousAccountTransfersServiceImpl implements SuspiciousAccountTransfersService{
    private final SuspiciousAccountTransfersRepository suspiciousAccountTransfersRepository;

    public SuspiciousAccountTransfersServiceImpl(SuspiciousAccountTransfersRepository suspiciousAccountTransfersRepository) {
        this.suspiciousAccountTransfersRepository = suspiciousAccountTransfersRepository;
    }

    @Override
    @Transactional
    public SuspiciousAccountTransfersEntity save(SuspiciousAccountTransfersDto suspiciousAccountTransfersDto) {

        SuspiciousAccountTransfersEntity suspiciousAccountTransfersEntity = SuspiciousAccountTransfersMapper
                .INSTANCE.suspiciousAccountTransfersDtoToSuspiciousAccountTransfersEntity(suspiciousAccountTransfersDto);
        return suspiciousAccountTransfersRepository.save(suspiciousAccountTransfersEntity);
    }

    @Override
    public List<SuspiciousAccountTransfersEntity> findAll() {
        return suspiciousAccountTransfersRepository.findAll();
    }

    @Override
    public SuspiciousAccountTransfersEntity findById(BigInteger id) {
        Optional<SuspiciousAccountTransfersEntity> suspiciousAccountTransfersEntity = suspiciousAccountTransfersRepository.findById(id);
        if (suspiciousAccountTransfersEntity.isPresent()) {
            return suspiciousAccountTransfersEntity.get();
        } else {
            throw new EntityNotFoundException("сущность SuspiciousAccountTransfers с id: " + id + " не найдена.");
        }
    }

    @Override
    @Transactional
    public void delete(BigInteger id) {
        SuspiciousAccountTransfersEntity suspiciousAccountTransfersEntity = suspiciousAccountTransfersRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("сущность SuspiciousAccountTransfers с id: " + id + " не найдена."));

        suspiciousAccountTransfersRepository.delete(suspiciousAccountTransfersEntity);
    }

    @Override
    @Transactional
    public SuspiciousAccountTransfersEntity update(SuspiciousAccountTransfersDto suspiciousAccountTransfersDto) {
        suspiciousAccountTransfersRepository.findById(suspiciousAccountTransfersDto.id())
                .orElseThrow(() -> new EntityNotFoundException(suspiciousAccountTransfersDto.id().toString()));
        return suspiciousAccountTransfersRepository.save(SuspiciousAccountTransfersMapper
                .INSTANCE.suspiciousAccountTransfersDtoToSuspiciousAccountTransfersEntity(suspiciousAccountTransfersDto));
    }
}
