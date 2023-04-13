package com.bank.transfer.service;

import com.bank.transfer.dto.AuditDto;
import com.bank.transfer.entity.AuditEntity;

import java.math.BigInteger;
import java.util.List;

public interface AuditService {
    List<AuditEntity> getAll();
    AuditEntity getById(BigInteger id);
    AuditEntity saveOrUpdate(AuditDto auditDto);
    void delete(BigInteger id);
}
