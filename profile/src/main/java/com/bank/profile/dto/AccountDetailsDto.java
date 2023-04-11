package com.bank.profile.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * DTO удаленного микросервиса account
*/
@AllArgsConstructor
@Setter
@Getter
@Builder
public class AccountDetailsDto {
    private Long id;
    private Long passportId;
    private BigInteger accountNumber;
    private Long bankDetailsId;
    private BigDecimal money;
    private boolean negativeBalance;
    private Long profileId;
}
