package com.bank.publicinfo.dto;

import java.math.BigInteger;
import java.time.LocalDateTime;

public record AtmDto(BigInteger id,
                     String address,
                     LocalDateTime startOfWork,
                     LocalDateTime endOfWork,
                     boolean allHours,
                     BranchDto branchDto) {
}
