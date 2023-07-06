package com.bank.publicinfo.dto;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Set;

public record BankDetailsDto(Long id,
                             @NotNull
                             BigInteger bik,
                             @NotNull
                             BigInteger inn,
                             @NotNull
                             BigInteger kpp,
                             @NotNull
                             Integer corAccount,
                             @NotBlank
                             @Length(max = 180)
                             String city,
                             @NotBlank
                             @Length(max = 15)
                             String jointStockCompany,
                             @NotBlank
                             @Length(max = 80)
                             String name,
                             Set<CertificateDto> certificates,
                             Set<LicenseDto> licenses) {
}
