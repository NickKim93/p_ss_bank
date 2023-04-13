package com.bank.publicinfo.dto;

import java.math.BigInteger;
import java.util.Set;

public record BankDetailsDto(Long id,
                             BigInteger bik,
                             BigInteger inn,
                             BigInteger kpp,
                             Integer corAccount,
                             String city,
                             String jointStockCompany,
                             String name,
                             Set<CertificateDto> certificates,
                             Set<LicenseDto> licenses) {
}
