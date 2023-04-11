package com.bank.profile.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

/**
 * DTO сущности адреса прописки
 * */
@AllArgsConstructor
@Setter
@Getter
@Builder
public class RegistrationDto {
    private Long id;
    @NotNull
    @Size(max = 166)
    private String country;
    @Size(max = 160)
    private String region;
    @Size(max = 160)
    private String city;
    @Size(max = 160)
    private String district;
    @Size(max = 230)
    private String locality;
    @Size(max = 230)
    private String street;
    @Size(max = 20)
    private String houseNumber;
    @Size(max = 20)
    private String houseBlock;
    @Size(max = 40)
    private String flatNumber;
    @NotNull
    @Min(value = 100000L)
    @Max(value = 999999L)
    private Long index;
}
