package com.bank.publicinfo.dto;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Set;

public record BranchDto(BigInteger id,
                        String address,
                        BigInteger phoneNumber,
                        String city,
                        LocalDateTime startOfWork,
                        LocalDateTime endOfWork,
                        Set<AtmDto> atmList) {
}
