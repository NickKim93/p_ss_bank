package com.bank.antifraud.service;

import com.bank.antifraud.dto.SuspiciousCardTransferDto;
import com.bank.antifraud.entity.SuspiciousCardTransferEntity;

import java.math.BigInteger;
import java.util.List;

public interface SuspiciousCardTransferService {
    public SuspiciousCardTransferEntity save(SuspiciousCardTransferDto suspiciousCardTransferDto);
    public SuspiciousCardTransferEntity findById(BigInteger id);
    public List<SuspiciousCardTransferEntity> findAll();
    public void delete(BigInteger id);
    public SuspiciousCardTransferEntity update(SuspiciousCardTransferDto suspiciousCardTransferDto);
}
