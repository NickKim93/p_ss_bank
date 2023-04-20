package com.bank.transfer.service;
import com.bank.transfer.entity.AuditEntity;
import java.util.List;
import java.util.Optional;

public interface AuditService {
    List<AuditEntity> getAll();
    Optional<AuditEntity> getById(Long id);
    void save(AuditEntity auditEntity);
}
