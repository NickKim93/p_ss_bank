package com.bank.profile.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;

@AllArgsConstructor
@Setter
@Getter
@Builder
public class AccountDetailsDto {
    private BigInteger id;
    private BigInteger passportId;
    private BigInteger accountNumber;
    private BigInteger bankDetailsId;
    private BigDecimal money;
    private boolean negativeBalance;
    private BigInteger profileId;
}
