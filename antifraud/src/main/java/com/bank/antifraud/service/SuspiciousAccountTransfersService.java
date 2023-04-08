package com.bank.antifraud.service;

import com.bank.antifraud.dto.AuditDto;
import com.bank.antifraud.dto.SuspiciousAccountTransfersDto;
import com.bank.antifraud.entity.AuditEntity;
import com.bank.antifraud.entity.SuspiciousAccountTransfersEntity;

import java.util.List;
import java.util.Optional;

public interface SuspiciousAccountTransfersService {
    public SuspiciousAccountTransfersEntity save(SuspiciousAccountTransfersDto suspiciousAccountTransfersDto);
    public SuspiciousAccountTransfersEntity findById(Long id);
    public List<SuspiciousAccountTransfersEntity> findAll();
    public void delete(Long id);
    public SuspiciousAccountTransfersEntity update(SuspiciousAccountTransfersDto suspiciousAccountTransfersDto);
}
