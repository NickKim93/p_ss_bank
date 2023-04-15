package com.bank.antifraud.audit;

import com.bank.antifraud.entity.AuditEntity;
import com.bank.antifraud.entity.Auditable;
import com.bank.antifraud.repository.AuditRepository;
import com.bank.antifraud.util.OperationType;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Aspect
@Component
public class AuditAspect {
    private final AuditRepository auditRepository;

    public AuditAspect(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    @AfterReturning(value = "@annotation(auditing)", returning = "ret")
    public void doAfterSaveAndUpdate(JoinPoint joinPoint, Auditing auditing, Object ret) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES);

        if (ret instanceof Auditable auditable) {
            AuditEntity auditEntity = new AuditEntity();

            if (auditing.operationType() == OperationType.CREATE) {
                auditEntity.setOperationType("CREATE");
                auditEntity.setEntityJson(mapper.writeValueAsString(auditable));
                auditEntity.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
            } else {
                AuditEntity firstAudit = auditRepository.findByEntityId(auditable.getId());
                auditEntity.setEntityJson(firstAudit.getEntityJson());
                auditEntity.setCreatedAt(firstAudit.getCreatedAt());
                auditEntity.setModifiedAt(Timestamp.valueOf(LocalDateTime.now()));
                auditEntity.setModifiedBy("system");

                if (auditing.operationType() == OperationType.UPDATE) {
                    auditEntity.setOperationType("UPDATE");
                    auditEntity.setNewEntityJson(mapper.writeValueAsString(auditable));
                } else {
                    auditEntity.setOperationType("DELETE");
                    auditEntity.setNewEntityJson("");
                }
            }
            auditEntity.setEntityType("transfer");
            auditEntity.setCreatedBy("system");
            auditRepository.save(auditEntity);
        }
    }

    @After(value = "@annotation(auditing)")
    public void DoAfterDelete(JoinPoint joinPoint, Auditing auditing) {
        if (auditing.operationType() != OperationType.DELETE) return;
        Long id = (Long) joinPoint.getArgs()[0];

        AuditEntity auditEntity = new AuditEntity();
        AuditEntity firstAudit = auditRepository.findByEntityId(id);

        auditEntity.setEntityType("transfer");
        auditEntity.setCreatedBy("system");
        auditEntity.setEntityJson(firstAudit.getEntityJson());
        auditEntity.setCreatedAt(firstAudit.getCreatedAt());
        auditEntity.setModifiedAt(Timestamp.valueOf(LocalDateTime.now()));
        auditEntity.setModifiedBy("system");
        auditEntity.setOperationType("DELETE");

        auditRepository.save(auditEntity);
    }
}
