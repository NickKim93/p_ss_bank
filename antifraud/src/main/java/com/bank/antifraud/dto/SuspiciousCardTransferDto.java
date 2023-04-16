package com.bank.antifraud.dto;

import com.bank.antifraud.entity.SuspiciousAccountTransfersEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * DTO для {@link com.bank.antifraud.entity.SuspiciousCardTransferEntity}
 *
 * @param id                технический идентификатор
 * @param cardTransferId технический идентификатор на перевод
 * @param isBlocked         заблокирован ли
 * @param isSuspicious      подозрительный ли
 * @param blockedReason     причина блокировки
 * @param suspiciousReason  причина почему перевод попал в antifraud
 * @author Makariy Petrov
 */
public record SuspiciousCardTransferDto
        (Long id,
         @NotNull
         Long cardTransferId,
         Boolean isBlocked,
         Boolean isSuspicious,
         String blockedReason,
         @NotBlank
         String suspiciousReason) {
}