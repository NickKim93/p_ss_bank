package com.bank.publicinfo.dto;

public record LicenseDto(Long id,
                         byte[] photo,
                         Long bankDetailsId) {
}
