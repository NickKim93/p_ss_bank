package com.bank.account.service;

import com.bank.account.entity.Audit;
import com.bank.account.repository.AuditRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuditServiceImp implements AuditService {
    private final AuditRepository repository;

    @Override
    public Audit getAudit(Long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Audit> getAllAudits() {
        return repository.findAll();
    }
}
