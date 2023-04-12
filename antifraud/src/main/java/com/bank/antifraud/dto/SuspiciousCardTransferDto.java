package com.bank.antifraud.dto;

public record SuspiciousCardTransferDto
        (Long id,
         Long cardTransferId,
         Boolean isBlocked,
         Boolean isSuspicious,
         String blockedReason,
         String suspiciousReason) { }