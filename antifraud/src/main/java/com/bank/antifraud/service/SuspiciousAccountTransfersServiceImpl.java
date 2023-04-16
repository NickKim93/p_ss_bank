package com.bank.antifraud.service;

import com.bank.antifraud.aspect.Auditing;
import com.bank.antifraud.dto.SuspiciousAccountTransfersDto;
import com.bank.antifraud.entity.SuspiciousAccountTransfersEntity;
import com.bank.antifraud.mapper.SuspiciousAccountTransfersMapper;
import com.bank.antifraud.repository.SuspiciousAccountTransfersRepository;
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
public class SuspiciousAccountTransfersServiceImpl implements SuspiciousAccountTransfersService{
    private final SuspiciousAccountTransfersRepository suspiciousAccountTransfersRepository;

    public SuspiciousAccountTransfersServiceImpl(SuspiciousAccountTransfersRepository suspiciousAccountTransfersRepository) {
        this.suspiciousAccountTransfersRepository = suspiciousAccountTransfersRepository;
    }

    @Override
    @Transactional
    @Auditing(operationType = OperationType.CREATE)
    public SuspiciousAccountTransfersEntity save(SuspiciousAccountTransfersDto dto) {
        SuspiciousAccountTransfersEntity suspiciousAccountTransfersEntity = SuspiciousAccountTransfersMapper
                .INSTANCE.suspiciousAccountTransfersDtoToSuspiciousAccountTransfersEntity(dto);

        return suspiciousAccountTransfersRepository.save(suspiciousAccountTransfersEntity);
    }

    @Override
    public List<SuspiciousAccountTransfersEntity> findAll() {
        return suspiciousAccountTransfersRepository.findAll();
    }

    @Override
    public SuspiciousAccountTransfersEntity findById(Long id) {
        Optional<SuspiciousAccountTransfersEntity> suspiciousAccountTransfersEntity = suspiciousAccountTransfersRepository.findById(id);

        if (suspiciousAccountTransfersEntity.isPresent()) {
            return suspiciousAccountTransfersEntity.get();
        } else {
            throw new EntityNotFoundException("сущность SuspiciousAccountTransfers с id: " + id + " не найдена.");
        }
    }

    @Override
    @Transactional
    @Auditing(operationType = OperationType.DELETE)
    public void delete(Long id) {
        SuspiciousAccountTransfersEntity suspiciousAccountTransfersEntity = suspiciousAccountTransfersRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("сущность SuspiciousAccountTransfers с id: " + id + " не найдена."));

        suspiciousAccountTransfersRepository.delete(suspiciousAccountTransfersEntity);
    }

    @Override
    @Transactional
    @Auditing(operationType = OperationType.UPDATE)
    public SuspiciousAccountTransfersEntity update(SuspiciousAccountTransfersDto dto) {
        SuspiciousAccountTransfersEntity updateEntity = suspiciousAccountTransfersRepository.findById(dto.id())
                .orElseThrow(() -> new EntityNotFoundException(dto.id().toString()));

        BeanUtils.copyProperties(dto, updateEntity);
        return suspiciousAccountTransfersRepository.save(updateEntity);
    }
}
