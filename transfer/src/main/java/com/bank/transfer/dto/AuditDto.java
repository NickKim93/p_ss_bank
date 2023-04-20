package com.bank.transfer.dto;

import java.time.LocalDateTime;
public record AuditDto(Long id,
                       String entityType,
                       String operationType,
                       String createdBy,
                       String modifiedBy,
                       LocalDateTime createdAt,
                       LocalDateTime modifiedAt,
                       String newEntityJson,
                       String entityJson) {
}
