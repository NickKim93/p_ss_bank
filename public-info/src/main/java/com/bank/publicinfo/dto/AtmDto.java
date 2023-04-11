package com.bank.publicinfo.dto;

import com.bank.publicinfo.entity.Branch;

import java.math.BigInteger;
import java.time.LocalDateTime;

public record AtmDto(Long id,
                     String address,
                     LocalDateTime startOfWork,
                     LocalDateTime endOfWork,
                     boolean allHours,
                     Long branchId) {
}
