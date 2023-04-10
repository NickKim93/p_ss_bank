package com.bank.antifraud.service;

import com.bank.antifraud.dto.AuditDto;
import com.bank.antifraud.entity.AuditEntity;
import com.bank.antifraud.mapper.AuditMapper;
import com.bank.antifraud.repository.AuditRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

/**
 * Бизнес логика для сущности Audit
 * @author Makariy Petrov
 */
@Service
public class AuditServiceImpl implements AuditService{
    private final AuditRepository auditRepository;

    public AuditServiceImpl(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    @Override
    public AuditEntity save(AuditDto auditDto) {
        AuditEntity auditEntity = AuditMapper.INSTANCE.auditDtoToAuditEntity(auditDto);
        return auditRepository.save(auditEntity);
    }

    @Override
    public List<AuditEntity> findAll() {
        return auditRepository.findAll();
    }

    @Override
    public AuditEntity findById(BigInteger id) {
        Optional<AuditEntity> auditEntity = auditRepository.findById(id);
        if (auditEntity.isPresent()) {
            return auditEntity.get();
        } else {
            throw new EntityNotFoundException("сущность Audit с id: " + id + " не найдена.");
        }
    }

    @Override
    public void delete(BigInteger id) {
        AuditEntity auditEntity = auditRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id.toString()));
        auditRepository.delete(auditEntity);
    }

    @Override
    public AuditEntity update(AuditDto auditDto) {
        auditRepository.findById(auditDto.id())
                .orElseThrow(() -> new EntityNotFoundException(auditDto.id().toString()));
        return auditRepository.save(AuditMapper.INSTANCE.auditDtoToAuditEntity(auditDto));
    }
}
