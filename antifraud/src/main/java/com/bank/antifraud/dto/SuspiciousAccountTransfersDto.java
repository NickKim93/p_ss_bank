package com.bank.antifraud.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record SuspiciousAccountTransfersDto
        (Long id,
         @NotNull
         Long accountTransferId,
         Boolean isBlocked,
         Boolean isSuspicious,
         String blockedReason,
         @NotBlank
         String suspiciousReason) { }