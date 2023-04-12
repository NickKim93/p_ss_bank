package com.bank.antifraud.dto;

public record SuspiciousAccountTransfersDto
        (Long id,
         Long accountTransferId,
         Boolean isBlocked,
         Boolean isSuspicious,
         String blockedReason,
         String suspiciousReason) { }