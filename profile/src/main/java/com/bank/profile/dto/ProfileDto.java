package com.bank.profile.dto;

import lombok.*;

import javax.validation.constraints.*;
import java.util.Set;

/**
 * DTO сущности профиля
 * */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ProfileDto {
    private Long id;
    @NotNull
    @Min(value = 1000000000L, message = "Номер телефона должен состоять из 10 цифр")
    @Max(value = 9999999999L, message = "Номер телефона должен состоять из 10 цифр")
    private Long phoneNumber;
    @Size(max = 264, message = "Допустимое число символов в поле \"email\" = 264")
    @Email(message = "Адрес электронной почты должен иметь вид 'email@mail.com'")
    private String email;
    @Size(max = 370, message = "Допустимое число символов в поле \"nameOnCard\" = 370")
    private String nameOnCard;
    @Min(value = 100000000000L, message = "ИНН должен состоять из 12 цифр")
    @Max(value = 999999999999L, message = "ИНН должен состоять из 12 цифр")
    private Long inn;
    @Min(value = 10000000000L, message = "СНИЛС должен состоять из 11 цифр")
    @Max(value = 99999999999L, message = "СНИЛС должен состоять из 11 цифр")
    private Long snils;
    @NotNull
    private PassportDto passport;
    private ActualRegistrationDto actualRegistration;
    private Set<AccountDetailsDto> accountDetailsSet;

}
