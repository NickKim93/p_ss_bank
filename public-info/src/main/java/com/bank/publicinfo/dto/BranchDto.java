package com.bank.publicinfo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Set;

public record BranchDto(Long id,
                        @NotBlank
                        String address,
                        BigInteger phoneNumber,
                        String city,
                        @NotNull
                        LocalDateTime startOfWork,
                        @NotNull
                        LocalDateTime endOfWork,
                        Set<AtmDto> atmList) {
}
