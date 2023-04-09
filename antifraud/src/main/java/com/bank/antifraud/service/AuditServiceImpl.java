package com.bank.antifraud.service;

import com.bank.antifraud.dto.AuditDto;
import com.bank.antifraud.entity.AuditEntity;
import com.bank.antifraud.mapper.AuditMapper;
import com.bank.antifraud.repository.AuditRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

/**
 * Бизнес логика для сущности Audit
 * @author Makariy Petrov
 */
@Service
@Transactional(readOnly = true)
public class AuditServiceImpl implements AuditService{
    private final AuditRepository auditRepository;

    public AuditServiceImpl(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    @Override
    @Transactional(readOnly = false)
    public AuditDto save(AuditDto auditDto) {
        AuditEntity auditEntity = AuditMapper.INSTANCE.auditDtoToAuditEntity(auditDto);
        return AuditMapper.INSTANCE.auditEntityToAuditDto(auditRepository.save(auditEntity));
    }

    @Override
    public List<AuditDto> findAll() {
        return auditRepository.findAll().stream()
                .map(AuditMapper.INSTANCE::auditEntityToAuditDto).toList();
    }

    @Override
    public AuditDto findById(BigInteger id) {
        Optional<AuditEntity> auditEntity = auditRepository.findById(id);
        if (auditEntity.isPresent()) {
            return AuditMapper.INSTANCE.auditEntityToAuditDto(auditEntity.get());
        } else {
            throw new EntityNotFoundException("сущность Audit с id: " + id + " не найдена.");
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(BigInteger id) {
        AuditEntity auditEntity = auditRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id.toString()));
        auditRepository.delete(auditEntity);
    }

    @Override
    @Transactional(readOnly = false)
    public AuditDto update(AuditDto auditDto) {
        auditRepository.findById(auditDto.id())
                .orElseThrow(() -> new EntityNotFoundException(auditDto.id().toString()));
        return AuditMapper.INSTANCE
                .auditEntityToAuditDto(auditRepository.save(AuditMapper.INSTANCE
                        .auditDtoToAuditEntity(auditDto)));
    }
}
