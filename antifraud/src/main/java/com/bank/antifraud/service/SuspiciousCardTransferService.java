package com.bank.antifraud.service;

import com.bank.antifraud.dto.SuspiciousCardTransferDto;
import com.bank.antifraud.entity.SuspiciousCardTransferEntity;

import java.util.List;

public interface SuspiciousCardTransferService {
    SuspiciousCardTransferEntity save(SuspiciousCardTransferDto suspiciousCardTransferDto);
    SuspiciousCardTransferEntity findById(Long id);
    List<SuspiciousCardTransferEntity> findAll();
    void delete(Long id);
    SuspiciousCardTransferEntity update(SuspiciousCardTransferDto suspiciousCardTransferDto);
}
