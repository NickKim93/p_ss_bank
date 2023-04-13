package com.bank.publicinfo.dto;

public record CertificateDto(Long id,
                             byte[] photo,
                             Long bankDetailsId) {
}
