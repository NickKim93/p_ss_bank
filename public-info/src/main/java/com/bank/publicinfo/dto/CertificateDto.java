package com.bank.publicinfo.dto;

import javax.validation.constraints.NotNull;

public record CertificateDto(Long id,
                             @NotNull
                             byte[] photo,
                             Long bankDetailsId) {
}
