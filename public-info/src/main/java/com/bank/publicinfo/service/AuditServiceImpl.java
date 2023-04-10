package com.bank.publicinfo.service;

import com.bank.publicinfo.dto.AuditDto;
import com.bank.publicinfo.entity.Audit;
import com.bank.publicinfo.mapper.AuditMapper;
import com.bank.publicinfo.repository.AuditRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuditServiceImpl implements AuditService {

    private final AuditRepository auditRepository;
    private final AuditMapper auditMapper;

    public AuditServiceImpl(AuditRepository auditRepository, AuditMapper auditMapper) {
        this.auditRepository = auditRepository;
        this.auditMapper = auditMapper;
    }

    @Override
    public AuditDto getAuditById(BigInteger id) {
        Audit audit = auditRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return auditMapper.AuditEntityToDto(audit);
    }

    @Override
    public List<AuditDto> getAudits() {
        List<Audit> auditEntities = auditRepository.findAll();
        return auditEntities.stream()
                .map(auditMapper::AuditEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AuditDto createAudit(AuditDto auditDto) {
        Audit audit = auditMapper.AuditDtoToEntity(auditDto);
        audit = auditRepository.save(audit);
        return auditMapper.AuditEntityToDto(audit);
    }

    @Override
    public AuditDto updateAudit(BigInteger id, AuditDto auditDto) {
        Audit audit = auditRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        audit = auditMapper.updateEntityFromDto(auditDto, audit);
        audit = auditRepository.save(audit);
        return auditMapper.AuditEntityToDto(audit);
    }

    @Override
    public void deleteAuditById(BigInteger id) {
           auditRepository.deleteById(id);
    }
}
