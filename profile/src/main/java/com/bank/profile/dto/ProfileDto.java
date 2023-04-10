package com.bank.profile.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.Set;

@AllArgsConstructor
@Setter
@Getter
@Builder
public class ProfileDto {
    private BigInteger id;
    private Long phoneNumber;
    private String email;
    private String nameOnCard;
    private Long inn;
    private Long snils;
    private PassportDto passport;
    private ActualRegistrationDto actualRegistration;
    private Set<AccountDetailsDto> accountDetailsSet;

}
