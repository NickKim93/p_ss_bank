package com.bank.antifraud.service;

import com.bank.antifraud.dto.AuditDto;
import com.bank.antifraud.dto.SuspiciousCardTransferDto;
import com.bank.antifraud.entity.AuditEntity;
import com.bank.antifraud.entity.SuspiciousCardTransferEntity;

import java.util.List;
import java.util.Optional;

public interface SuspiciousCardTransferService {
    public SuspiciousCardTransferEntity save(SuspiciousCardTransferDto suspiciousCardTransferDto);
    public SuspiciousCardTransferEntity findById(Long id);
    public List<SuspiciousCardTransferEntity> findAll();
    public void delete(Long id);
    public SuspiciousCardTransferEntity update(SuspiciousCardTransferDto suspiciousCardTransferDto);
}
