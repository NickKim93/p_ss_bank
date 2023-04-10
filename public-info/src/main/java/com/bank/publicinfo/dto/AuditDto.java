package com.bank.publicinfo.dto;

import java.math.BigInteger;
import java.time.LocalDateTime;

public record AuditDto(BigInteger id,
                       String entityType,
                       String operationType,
                       String createdBy,
                       String modifiedBy,
                       LocalDateTime createdAt,
                       LocalDateTime modifiedAt,
                       String newEntityJson,
                       String entityJson) {
}
