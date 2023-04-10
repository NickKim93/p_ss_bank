package com.bank.antifraud.dto;

import java.math.BigInteger;

public record SuspiciousPhoneTransfersDto
        (BigInteger id,
         Long phoneTransferId,
         Boolean isBlocked,
         Boolean isSuspicious,
         String blockedReason,
         String suspiciousReason) { }