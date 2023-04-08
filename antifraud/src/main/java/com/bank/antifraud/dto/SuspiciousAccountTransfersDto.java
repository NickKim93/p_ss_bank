package com.bank.antifraud.dto;

import java.math.BigInteger;

public record SuspiciousAccountTransfersDto
        (BigInteger id,
         Long accountTransferId,
         Boolean isBlocked,
         Boolean isSuspicious,
         String blockedReason,
         String suspiciousReason) { }