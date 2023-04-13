package com.bank.antifraud.util;

import com.bank.antifraud.entity.AuditEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class AuditingEntityListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuditingEntityListener.class);

    @PrePersist
    public void prePersist(Object obj) {
        if (obj instanceof AuditEntity auditable) {
            addAuditData(auditable, OperationType.CREATE);
        }
    }

    @PreUpdate
    public void preUpdate(Object obj) {
        if (obj instanceof AuditEntity auditable) {
            addAuditData(auditable, OperationType.UPDATE);
        }
    }

    private void addAuditData(AuditEntity auditable, OperationType operationType) {
        LOGGER.info("В AuditingEntityListener сработал метод addAuditData");
        String username = "system";
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String newEntityJson = objectMapper.writeValueAsString(auditable);

            auditable.setEntityType(auditable.getClass().getSimpleName());
            auditable.setOperationType(operationType.toString());
            auditable.setModifiedAt(now);
            auditable.setNewEntityJson(newEntityJson);
            auditable.setModifiedBy(username);

            if (operationType == OperationType.CREATE) {
                auditable.setCreatedBy(username);
                auditable.setCreatedAt(now);
                auditable.setEntityJson(newEntityJson);
            }
        } catch (JsonProcessingException e) {
            LOGGER.error("Ошибка преобразования объекта в JSON");
        }
    }
}