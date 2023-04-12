package com.bank.antifraud.service;

import com.bank.antifraud.dto.SuspiciousPhoneTransfersDto;
import com.bank.antifraud.entity.SuspiciousPhoneTransfersEntity;

import java.util.List;

public interface SuspiciousPhoneTransfersService {
    public SuspiciousPhoneTransfersEntity save(SuspiciousPhoneTransfersDto suspiciousPhoneTransfersDto);
    public SuspiciousPhoneTransfersEntity findById(Long id);
    public List<SuspiciousPhoneTransfersEntity> findAll();
    public void delete(Long id);
    public SuspiciousPhoneTransfersEntity update(SuspiciousPhoneTransfersDto suspiciousPhoneTransfersDto);
}
