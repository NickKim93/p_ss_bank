package com.bank.antifraud.service;

import com.bank.antifraud.dto.SuspiciousAccountTransfersDto;
import com.bank.antifraud.entity.SuspiciousAccountTransfersEntity;

import java.util.List;

public interface SuspiciousAccountTransfersService {
    public SuspiciousAccountTransfersEntity save(SuspiciousAccountTransfersDto suspiciousAccountTransfersDto);
    public SuspiciousAccountTransfersEntity findById(Long id);
    public List<SuspiciousAccountTransfersEntity> findAll();
    public void delete(Long id);
    public SuspiciousAccountTransfersEntity update(SuspiciousAccountTransfersDto suspiciousAccountTransfersDto);
}
