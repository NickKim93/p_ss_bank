package com.bank.publicinfo.dto;

import java.time.LocalDateTime;

public record AtmDto(Long id,
                     String address,
                     LocalDateTime startOfWork,
                     LocalDateTime endOfWork,
                     boolean allHours,
                     Long branchId) {
}
