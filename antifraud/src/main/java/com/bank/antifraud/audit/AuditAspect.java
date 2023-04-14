package com.bank.antifraud.audit;

import com.bank.antifraud.entity.AuditEntity;
import com.bank.antifraud.entity.Auditable;
import com.bank.antifraud.repository.AuditRepository;
import com.bank.antifraud.util.OperationType;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public void save(JoinPoint joinPoint, Auditing auditing, Object ret) throws JsonProcessingException {
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
                auditEntity.setOperationType("UPDATE");
                auditEntity.setEntityJson(firstAudit.getEntityJson());
                auditEntity.setCreatedAt(firstAudit.getCreatedAt());
                auditEntity.setNewEntityJson(mapper.writeValueAsString(auditable));
                auditEntity.setModifiedAt(Timestamp.valueOf(LocalDateTime.now()));
                auditEntity.setModifiedBy("system");
            }
            auditEntity.setEntityType("transfer");
            auditEntity.setCreatedBy("system");

            auditRepository.save(auditEntity);
        }
    }
}
