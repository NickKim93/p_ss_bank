package com.bank.transfer.dto;

import java.math.BigInteger;
import java.time.OffsetDateTime;

/**
 * Класс, описывающий Data Transfer Objects Audit
 * @author Savenkov Artem
 */
public record AuditDto(BigInteger id,
                       String entityType,
                       String operationType,
                       String createdBy,
                       String modifiedBy,
                       OffsetDateTime createdAt,
                       OffsetDateTime modifiedAt,
                       String newEntityJson,
                       String entityJson) {
}
