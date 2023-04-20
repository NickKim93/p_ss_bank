package com.bank.antifraud.service;

import com.bank.antifraud.entity.AuditEntity;
import com.bank.antifraud.repository.AuditRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class AuditServiceImpl implements AuditService {
    private final AuditRepository auditRepository;

    public AuditServiceImpl(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    @Override
    public List<AuditEntity> findAll() {
        return auditRepository.findAll();
    }
}