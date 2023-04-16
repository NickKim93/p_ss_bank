package com.bank.antifraud.service;

import com.bank.antifraud.aspect.Auditing;
import com.bank.antifraud.dto.SuspiciousCardTransferDto;
import com.bank.antifraud.entity.SuspiciousCardTransferEntity;
import com.bank.antifraud.mapper.SuspiciousCardTransferMapper;
import com.bank.antifraud.repository.SuspiciousCardTransferRepository;
import com.bank.antifraud.util.OperationType;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SuspiciousCardTransferServiceImpl implements SuspiciousCardTransferService{
    private final SuspiciousCardTransferRepository suspiciousCardTransferRepository;

    public SuspiciousCardTransferServiceImpl(SuspiciousCardTransferRepository suspiciousCardTransferRepository) {
        this.suspiciousCardTransferRepository = suspiciousCardTransferRepository;
    }

    @Override
    @Transactional
    @Auditing(operationType = OperationType.CREATE)
    public SuspiciousCardTransferEntity save(SuspiciousCardTransferDto suspiciousCardTransferDto) {
        SuspiciousCardTransferEntity suspiciousCardTransferEntity = SuspiciousCardTransferMapper
                .INSTANCE.suspiciousCardTransferDtoToSuspiciousCardTransferEntity(suspiciousCardTransferDto);

        return suspiciousCardTransferRepository.save(suspiciousCardTransferEntity);
    }

    @Override
    public SuspiciousCardTransferEntity findById(Long id) {
        Optional<SuspiciousCardTransferEntity> suspiciousCardTransferEntity = suspiciousCardTransferRepository.findById(id);

        if (suspiciousCardTransferEntity.isPresent()) {
            return suspiciousCardTransferEntity.get();
        } else {
            throw new EntityNotFoundException("сущность SuspiciousCardTransfer с id: " + id + " не найдена.");
        }
    }

    @Override
    public List<SuspiciousCardTransferEntity> findAll() {
        return suspiciousCardTransferRepository.findAll();
    }

    @Override
    @Transactional
    @Auditing(operationType = OperationType.DELETE)
    public void delete(Long id) {
        SuspiciousCardTransferEntity suspiciousCardTransferEntity = suspiciousCardTransferRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("сущность SuspiciousCardTransfer с id: " + id + " не найдена."));

        suspiciousCardTransferRepository.delete(suspiciousCardTransferEntity);
    }

    @Override
    @Transactional()
    @Auditing(operationType = OperationType.UPDATE)
    public SuspiciousCardTransferEntity update(SuspiciousCardTransferDto suspiciousCardTransferDto) {
        SuspiciousCardTransferEntity updateEntity = suspiciousCardTransferRepository.findById(suspiciousCardTransferDto.id())
                .orElseThrow(() -> new EntityNotFoundException(suspiciousCardTransferDto.id().toString()));

        BeanUtils.copyProperties(suspiciousCardTransferDto, updateEntity);
        return suspiciousCardTransferRepository.save(updateEntity);
    }
}
