package com.bank.transfer.service;

import com.bank.transfer.entity.AuditEntity;
import com.bank.transfer.repository.AuditRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
public class AuditServiceImpl implements AuditService {

    private final AuditRepository auditRepository;

    public AuditServiceImpl(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    @Override
    public List<AuditEntity> getAll() {
        return auditRepository.findAll();
    }

    @Override
    public Optional<AuditEntity> getById(Long id) {
        return auditRepository.findById(id);
    }

    @Override
    @Transactional
    public void save(AuditEntity auditEntity) {
        log.info("try to save auditEntity: {}", auditEntity);
        auditRepository.save(auditEntity);
        log.info("save auditEntity success, id={}", auditEntity.getId());
    }
}
