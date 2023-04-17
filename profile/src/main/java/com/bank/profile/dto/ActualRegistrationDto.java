package com.bank.profile.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO сущности места текущего проживания
 */
@AllArgsConstructor
@Setter
@Getter
@Builder
@EqualsAndHashCode
public class ActualRegistrationDto {
    private Long id;

    @NotNull
    @Size(max = 166, message = "Допустимое число символов в поле \"country\" = 166")
    private String country;

    @Size(max = 160, message = "Допустимое число символов в поле \"region\" = 160")
    private String region;

    @Size(max = 160, message = "Допустимое число символов в поле \"city\" = 160")
    private String city;

    @Size(max = 160, message = "Допустимое число символов в поле \"district\" = 160")
    private String district;

    @Size(max = 230, message = "Допустимое число символов в поле \"locality\" = 230")
    private String locality;

    @Size(max = 230, message = "Допустимое число символов в поле \"street\" = 230")
    private String street;

    @Size(max = 20, message = "Допустимое число символов в поле \"houseNumber\" = 20")
    private String houseNumber;

    @Size(max = 20, message = "Допустимое число символов в поле \"houseBlock\" = 20")
    private String houseBlock;

    @Size(max = 40, message = "Допустимое число символов в поле \"flatNumber\" = 40")
    private String flatNumber;

    @NotNull
    @Min(value = 100000L, message = "Почтовый индекс должен состоять из 6 цифр")
    @Max(value = 999999L, message = "Почтовый индекс должен состоять из 6 цифр")
    private Long index;
}
