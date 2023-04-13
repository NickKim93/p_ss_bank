package com.bank.antifraud.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record SuspiciousPhoneTransfersDto
        (Long id,
         @NotNull
         Long phoneTransferId,
         Boolean isBlocked,
         Boolean isSuspicious,
         String blockedReason,
         @NotBlank
         String suspiciousReason) { }