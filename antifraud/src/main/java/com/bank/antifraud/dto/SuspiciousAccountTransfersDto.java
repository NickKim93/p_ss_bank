package com.bank.antifraud.dto;

import javax.persistence.Column;

public record SuspiciousAccountTransfersDto
        (Long id,
         Long accountTransferId,
         Boolean isBlocked,
         Boolean isSuspicious,
         String blockedReason,
         String suspiciousReason) { }