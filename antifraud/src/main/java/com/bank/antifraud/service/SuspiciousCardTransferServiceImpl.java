package com.bank.antifraud.service;

import com.bank.antifraud.dto.SuspiciousCardTransferDto;
import com.bank.antifraud.entity.SuspiciousCardTransferEntity;
import com.bank.antifraud.mapper.SuspiciousCardTransferMapper;
import com.bank.antifraud.repository.SuspiciousCardTransferRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class SuspiciousCardTransferServiceImpl implements SuspiciousCardTransferService{
    private final SuspiciousCardTransferRepository suspiciousCardTransferRepository;

    public SuspiciousCardTransferServiceImpl(SuspiciousCardTransferRepository suspiciousCardTransferRepository) {
        this.suspiciousCardTransferRepository = suspiciousCardTransferRepository;
    }

    @Override
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
    public void delete(Long id) {
        SuspiciousCardTransferEntity suspiciousCardTransferEntity = suspiciousCardTransferRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("сущность SuspiciousCardTransfer с id: " + id + " не найдена."));
        suspiciousCardTransferRepository.delete(suspiciousCardTransferEntity);
    }

    @Override
    public SuspiciousCardTransferEntity update(SuspiciousCardTransferDto suspiciousCardTransferDto) {
        suspiciousCardTransferRepository.findById(suspiciousCardTransferDto.id())
                .orElseThrow(() -> new EntityNotFoundException(suspiciousCardTransferDto.id().toString()));
        return suspiciousCardTransferRepository.save(SuspiciousCardTransferMapper
                .INSTANCE.suspiciousCardTransferDtoToSuspiciousCardTransferEntity(suspiciousCardTransferDto));
    }
}
