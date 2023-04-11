package com.bank.profile.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO сущности адреса прописки
 * */
@AllArgsConstructor
@Setter
@Getter
@Builder
public class RegistrationDto {
    private Long id;
    private String country;
    private String region;
    private String city;
    private String district;
    private String locality;
    private String street;
    private String houseNumber;
    private String houseBlock;
    private String flatNumber;
    private Long index;
}
