package com.bank.publicinfo.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record AtmDto(Long id,
                     String address,
                     @NotNull
                     LocalDateTime startOfWork,
                     @NotNull
                     LocalDateTime endOfWork,
                     @NotNull
                     boolean allHours,
                     Long branchId) {
}
