package com.bank.antifraud.service;

import com.bank.antifraud.dto.SuspiciousAccountTransfersDto;
import com.bank.antifraud.entity.SuspiciousAccountTransfersEntity;

import java.math.BigInteger;
import java.util.List;

public interface SuspiciousAccountTransfersService {
    public SuspiciousAccountTransfersEntity save(SuspiciousAccountTransfersDto suspiciousAccountTransfersDto);
    public SuspiciousAccountTransfersEntity findById(BigInteger id);
    public List<SuspiciousAccountTransfersEntity> findAll();
    public void delete(BigInteger id);
    public SuspiciousAccountTransfersEntity update(SuspiciousAccountTransfersDto suspiciousAccountTransfersDto);
}
