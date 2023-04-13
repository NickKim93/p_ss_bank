package com.bank.antifraud.service;

import com.bank.antifraud.dto.SuspiciousAccountTransfersDto;
import com.bank.antifraud.entity.SuspiciousAccountTransfersEntity;

import java.util.List;

public interface SuspiciousAccountTransfersService {
    SuspiciousAccountTransfersEntity save(SuspiciousAccountTransfersDto suspiciousAccountTransfersDto);
    SuspiciousAccountTransfersEntity findById(Long id);
    List<SuspiciousAccountTransfersEntity> findAll();
    void delete(Long id);
    SuspiciousAccountTransfersEntity update(SuspiciousAccountTransfersDto suspiciousAccountTransfersDto);
}
