package com.bank.antifraud.service;

import com.bank.antifraud.dto.AuditDto;
import com.bank.antifraud.entity.AuditEntity;

import java.math.BigInteger;
import java.util.List;

public interface AuditService {
    public AuditEntity save(AuditDto auditDto);
    public AuditEntity findById(BigInteger id);
    public List<AuditEntity> findAll();
    public void delete(BigInteger id);
    public AuditEntity update(AuditDto auditDto);
}
