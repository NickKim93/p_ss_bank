package com.bank.antifraud.service;

import com.bank.antifraud.dto.SuspiciousPhoneTransfersDto;
import com.bank.antifraud.entity.SuspiciousPhoneTransfersEntity;
import com.bank.antifraud.mapper.SuspiciousPhoneTransfersMapper;
import com.bank.antifraud.repository.SuspiciousPhoneTransfersRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
public class SuspiciousPhoneTransfersServiceImpl implements SuspiciousPhoneTransfersService{
    private final SuspiciousPhoneTransfersRepository suspiciousPhoneTransfersRepository;

    public SuspiciousPhoneTransfersServiceImpl(SuspiciousPhoneTransfersRepository suspiciousPhoneTransfersRepository) {
        this.suspiciousPhoneTransfersRepository = suspiciousPhoneTransfersRepository;
    }

    @Override
    public SuspiciousPhoneTransfersEntity save(SuspiciousPhoneTransfersDto suspiciousPhoneTransfersDto) {
        SuspiciousPhoneTransfersEntity suspiciousPhoneTransfersEntity = SuspiciousPhoneTransfersMapper.INSTANCE.suspiciousPhoneTransfersDtoToSuspiciousPhoneTransfersEntity(suspiciousPhoneTransfersDto);
        return suspiciousPhoneTransfersRepository.save(suspiciousPhoneTransfersEntity);
    }

    @Override
    public SuspiciousPhoneTransfersEntity findById(BigInteger id) {
        Optional<SuspiciousPhoneTransfersEntity> suspiciousPhoneTransfersEntity = suspiciousPhoneTransfersRepository.findById(id);
        if (suspiciousPhoneTransfersEntity.isPresent()) {
            return suspiciousPhoneTransfersEntity.get();
        } else {
            throw new EntityNotFoundException("сущность suspiciousPhoneTransfers с id: " + id + " не найдена.");
        }
    }

    @Override
    public List<SuspiciousPhoneTransfersEntity> findAll() {
        return suspiciousPhoneTransfersRepository.findAll();
    }

    @Override
    public void delete(BigInteger id) {
        SuspiciousPhoneTransfersEntity suspiciousPhoneTransfersEntity = suspiciousPhoneTransfersRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("сущность SuspiciousPhoneTransfers с id: " + id + " не найдена."));
        suspiciousPhoneTransfersRepository.delete(suspiciousPhoneTransfersEntity);
    }

    @Override
    public SuspiciousPhoneTransfersEntity update(SuspiciousPhoneTransfersDto suspiciousPhoneTransfersDto) {
        suspiciousPhoneTransfersRepository.findById(suspiciousPhoneTransfersDto.id())
                .orElseThrow(() -> new EntityNotFoundException(suspiciousPhoneTransfersDto.id().toString()));
        return suspiciousPhoneTransfersRepository.save(SuspiciousPhoneTransfersMapper
                .INSTANCE.suspiciousPhoneTransfersDtoToSuspiciousPhoneTransfersEntity(suspiciousPhoneTransfersDto));
    }
}
