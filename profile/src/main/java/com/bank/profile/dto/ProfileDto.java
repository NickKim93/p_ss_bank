package com.bank.profile.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.Set;

/**
 * DTO сущности профиля
 * */
@AllArgsConstructor
@Setter
@Getter
@Builder
public class ProfileDto {
    private Long id;
    @NotNull
    @Min(value = 1000000000L)
    @Max(value = 9999999999L)
    private Long phoneNumber;
    @Size(max = 264)
    @Email(message = "Почтовый адрес должен иметь вид 'email@mail.com'")
    private String email;
    @Size(max = 370)
    private String nameOnCard;
    @Min(value = 100000000000L)
    @Max(value = 999999999999L)
    private Long inn;
    @Min(value = 10000000000L)
    @Max(value = 99999999999L)
    private Long snils;
    @NotNull
    private PassportDto passport;
    private ActualRegistrationDto actualRegistration;
    private Set<AccountDetailsDto> accountDetailsSet;

}
