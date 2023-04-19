package com.bank.publicinfo.dto;

import javax.validation.constraints.NotNull;

public record LicenseDto(Long id,
                         @NotNull
                         byte[] photo,
                         Long bankDetailsId) {
}
