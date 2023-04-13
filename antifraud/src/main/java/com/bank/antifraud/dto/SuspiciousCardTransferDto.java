package com.bank.antifraud.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record SuspiciousCardTransferDto
        (Long id,
         @NotNull
         Long cardTransferId,
         Boolean isBlocked,
         Boolean isSuspicious,
         String blockedReason,
         @NotBlank
         String suspiciousReason) { }