package com.bank.profile.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDate;

/**
 * DTO сущности пасспорта
 * */
@AllArgsConstructor
@Setter
@Getter
@Builder
public class PassportDto {
    private Long id;
    @NotNull
    @Min(value = 1000L)
    @Max(value = 9999L)
    private Integer series;
    @NotNull
    @Min(value = 100000L)
    @Max(value = 999999L)
    private Long number;
    @NotNull
    @NotBlank
    @Size(max = 255)
    private String lastName;
    @NotNull
    @NotBlank
    @Size(max = 255)
    private String firstName;
    @Size(max = 255)
    private String middleName;
    @NotNull
    @NotBlank
    @Size(max = 3)
    private String gender;
    @NotNull
    private LocalDate birthDate;
    @NotNull
    @NotBlank
    @Size(max = 480)
    private String birthPlace;
    @NotNull
    @NotBlank
    private String issuedBy;
    @NotNull
    private LocalDate dateOfIssue;
    @NotNull
    @Min(value = 100000)
    @Max(value = 999999)
    private Integer divisionCode;
    private LocalDate expirationDate;
    @NotNull
    private RegistrationDto registration;
}
