package com.bank.antifraud.util;

import com.bank.antifraud.entity.AuditEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class AuditingEntityListener {

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
        String username = "system";
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String newEntityJson = objectMapper.writeValueAsString(auditable);
            auditable.setEntityType(auditable.getClass().getName());
            auditable.setOperationType(operationType.toString());
            auditable.setCreatedBy(username);
            auditable.setModifiedBy(username);
            auditable.setCreatedAt(now);
            auditable.setModifiedAt(now);
            auditable.setNewEntityJson(newEntityJson);
            auditable.setEntityJson(newEntityJson);
        } catch (JsonProcessingException e) {
            // handle exception
        }
    }
}
