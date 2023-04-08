package com.bank.antifraud.dto;

import javax.persistence.Column;
import java.sql.Timestamp;

public record AuditDto
        (Long id,
         String entityType,
         String operationType,
         String createdBy,
         String modifiedBy,
         Timestamp createdAt,
         Timestamp modifiedAt,
         String newEntityJson,
         String entityJson){ }
