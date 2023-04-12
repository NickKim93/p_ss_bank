package com.bank.antifraud.service;

import com.bank.antifraud.dto.SuspiciousCardTransferDto;
import com.bank.antifraud.entity.SuspiciousCardTransferEntity;

import java.util.List;

public interface SuspiciousCardTransferService {
    public SuspiciousCardTransferEntity save(SuspiciousCardTransferDto suspiciousCardTransferDto);
    public SuspiciousCardTransferEntity findById(Long id);
    public List<SuspiciousCardTransferEntity> findAll();
    public void delete(Long id);
    public SuspiciousCardTransferEntity update(SuspiciousCardTransferDto suspiciousCardTransferDto);
}
