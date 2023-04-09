package com.bank.antifraud.service;

import com.bank.antifraud.dto.AuditDto;
import com.bank.antifraud.entity.AuditEntity;

import java.math.BigInteger;
import java.util.List;

public interface AuditService {
    public AuditDto save(AuditDto auditDto);
    public AuditDto findById(BigInteger id);
    public List<AuditDto> findAll();
    public void delete(BigInteger id);
    public AuditDto update(AuditDto auditDto);
}
