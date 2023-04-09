package com.bank.antifraud.service;

import com.bank.antifraud.dto.SuspiciousPhoneTransfersDto;
import com.bank.antifraud.entity.SuspiciousPhoneTransfersEntity;
import com.bank.antifraud.mapper.SuspiciousPhoneTransfersMapper;
import com.bank.antifraud.repository.SuspiciousPhoneTransfersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SuspiciousPhoneTransfersServiceImpl implements SuspiciousPhoneTransfersService{
    private final SuspiciousPhoneTransfersRepository suspiciousPhoneTransfersRepository;

    public SuspiciousPhoneTransfersServiceImpl(SuspiciousPhoneTransfersRepository suspiciousPhoneTransfersRepository) {
        this.suspiciousPhoneTransfersRepository = suspiciousPhoneTransfersRepository;
    }

    @Override
    @Transactional(readOnly = false)
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
    @Transactional(readOnly = false)
    public void delete(BigInteger id) {
        SuspiciousPhoneTransfersEntity suspiciousPhoneTransfersEntity = suspiciousPhoneTransfersRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("сущность SuspiciousPhoneTransfers с id: " + id + " не найдена."));
        suspiciousPhoneTransfersRepository.delete(suspiciousPhoneTransfersEntity);
    }

    @Override
    @Transactional(readOnly = false)
    public SuspiciousPhoneTransfersEntity update(SuspiciousPhoneTransfersDto suspiciousPhoneTransfersDto) {
        suspiciousPhoneTransfersRepository.findById(suspiciousPhoneTransfersDto.id())
                .orElseThrow(() -> new EntityNotFoundException(suspiciousPhoneTransfersDto.id().toString()));
        return suspiciousPhoneTransfersRepository.save(SuspiciousPhoneTransfersMapper
                .INSTANCE.suspiciousPhoneTransfersDtoToSuspiciousPhoneTransfersEntity(suspiciousPhoneTransfersDto));
    }
}
