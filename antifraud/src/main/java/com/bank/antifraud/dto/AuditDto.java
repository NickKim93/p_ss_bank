package com.bank.antifraud.dto;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * Класс, описывающий Data Transfer Objects Audit
 * @author Makariy Petrov
 */
public record AuditDto
        (BigInteger id,
         String entityType,
         String operationType,
         String createdBy,
         String modifiedBy,
         Timestamp createdAt,
         Timestamp modifiedAt,
         String newEntityJson,
         String entityJson){ }
