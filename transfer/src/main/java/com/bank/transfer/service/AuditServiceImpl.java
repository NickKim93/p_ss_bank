package com.bank.transfer.service;

import com.bank.transfer.dto.AuditDto;
import com.bank.transfer.entity.AuditEntity;
import com.bank.transfer.mapper.AuditMapper;
import com.bank.transfer.repository.AuditRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
/**
 * Бизнес-логика приложения для Audit Entity
 * @author Savenkov Artem
 */
@Service
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
    public AuditEntity getById(BigInteger id) {
        Optional<AuditEntity> optional = auditRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new EntityNotFoundException("Audit Entity с id: " + id + " не найден");
        }
    }

    @Override
    public AuditEntity saveOrUpdate(AuditDto auditDto) {
        AuditEntity auditEntity = AuditMapper.getAuditMapper.dtoToEntityAudit(auditDto);
        return auditRepository.save(auditEntity);
    }

    @Override
    public void delete(BigInteger id) {
        AuditEntity auditEntity = auditRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException(id.toString()));
        auditRepository.delete(auditEntity);
    }
}
