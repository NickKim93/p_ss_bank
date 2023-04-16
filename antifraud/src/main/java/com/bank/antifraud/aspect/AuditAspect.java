package com.bank.antifraud.aspect;

import com.bank.antifraud.entity.AuditEntity;
import com.bank.antifraud.entity.Auditable;
import com.bank.antifraud.repository.AuditRepository;
import com.bank.antifraud.util.OperationType;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Класс, который проводит аудит методов, помеченных аннотацией @Auditing
 *
 * @author Makariy Petrov
 */
@Aspect
@Component
public class AuditAspect {
    private final AuditRepository auditRepository;

    public AuditAspect(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    @AfterReturning(value = "@annotation(auditing)", returning = "ret")
    public void doAfterSaveAndUpdate(Auditing auditing, Object ret) throws JsonProcessingException {
        if (ret instanceof Auditable auditable) {
            ObjectMapper mapper = new ObjectMapper();
            // Просим делать ключи в двойных кавычках, чтобы PostgreSQL правильно читал json при поиске
            mapper.enable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES);

            // Эти переменные есть в любом случае
            String entityJson;
            Timestamp createdAt;

            // Эти только при UPDATE
            Timestamp modifiedAt = null;
            String newEntityJson = null;

            if (auditing.operationType() == OperationType.CREATE) {
                entityJson = mapper.writeValueAsString(auditable);
                createdAt = Timestamp.valueOf(LocalDateTime.now());
            } else {
                // Ищем первую запись о данной сущности
                AuditEntity firstAudit = auditRepository.findByEntityId(auditable.getId());

                entityJson = firstAudit.getEntityJson();
                createdAt = firstAudit.getCreatedAt();
                modifiedAt = Timestamp.valueOf(LocalDateTime.now());
                newEntityJson = mapper.writeValueAsString(auditable);
            }

            AuditEntity auditEntity = createAuditEntity(auditing, createdAt, modifiedAt, newEntityJson, entityJson);

            auditRepository.save(auditEntity);
        }
    }

    @After(value = "@annotation(auditing)")
    public void DoAfterDelete(JoinPoint joinPoint, Auditing auditing) {
        if (auditing.operationType() != OperationType.DELETE) return;

        Long id = (Long) joinPoint.getArgs()[0];
        AuditEntity firstAudit = auditRepository.findByEntityId(id);

        AuditEntity auditEntity = createAuditEntity(auditing,
                firstAudit.getCreatedAt(),
                Timestamp.valueOf(LocalDateTime.now()),
                null,
                firstAudit.getEntityJson());

        auditRepository.save(auditEntity);
    }

    private AuditEntity createAuditEntity(Auditing auditing, Timestamp createdAt, Timestamp modifiedAt, String newEntityJson, String entityJson) {
        return new AuditEntity(
                null,
                "transfer",
                auditing.operationType().toString(),
                "system",
                "system",
                createdAt,
                modifiedAt,
                newEntityJson,
                entityJson);
    }
}
