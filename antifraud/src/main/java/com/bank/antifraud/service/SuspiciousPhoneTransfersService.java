package com.bank.antifraud.service;

import com.bank.antifraud.dto.SuspiciousPhoneTransfersDto;
import com.bank.antifraud.entity.SuspiciousPhoneTransfersEntity;

import java.util.List;

public interface SuspiciousPhoneTransfersService {
    SuspiciousPhoneTransfersEntity save(SuspiciousPhoneTransfersDto suspiciousPhoneTransfersDto);
    SuspiciousPhoneTransfersEntity findById(Long id);
    List<SuspiciousPhoneTransfersEntity> findAll();
    void delete(Long id);
    SuspiciousPhoneTransfersEntity update(SuspiciousPhoneTransfersDto suspiciousPhoneTransfersDto);
}
