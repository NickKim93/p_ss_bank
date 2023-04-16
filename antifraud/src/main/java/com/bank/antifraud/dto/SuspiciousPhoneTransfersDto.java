package com.bank.antifraud.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * DTO для {@link com.bank.antifraud.entity.SuspiciousPhoneTransfersEntity}
 *
 * @param id                технический идентификатор
 * @param phoneTransferId технический идентификатор на перевод
 * @param isBlocked         заблокирован ли
 * @param isSuspicious      подозрительный ли
 * @param blockedReason     причина блокировки
 * @param suspiciousReason  причина почему перевод попал в antifraud
 * @author Makariy Petrov
 */
public record SuspiciousPhoneTransfersDto
        (Long id,
         @NotNull
         Long phoneTransferId,
         Boolean isBlocked,
         Boolean isSuspicious,
         String blockedReason,
         @NotBlank
         String suspiciousReason) { }