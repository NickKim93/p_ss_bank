package com.bank.antifraud.dto;

public record SuspiciousPhoneTransfersDto
        (Long id,
         Long phoneTransferId,
         Boolean isBlocked,
         Boolean isSuspicious,
         String blockedReason,
         String suspiciousReason) { }