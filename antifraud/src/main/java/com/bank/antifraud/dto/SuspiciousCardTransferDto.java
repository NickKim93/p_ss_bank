package com.bank.antifraud.dto;

import java.math.BigInteger;

public record SuspiciousCardTransferDto
        (BigInteger id,
         Long cardTransferId,
         Boolean isBlocked,
         Boolean isSuspicious,
         String blockedReason,
         String suspiciousReason) { }