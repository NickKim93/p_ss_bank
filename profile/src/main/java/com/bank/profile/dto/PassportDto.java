package com.bank.profile.dto;

import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDate;

/**
 * DTO сущности пасспорта
 * */
@AllArgsConstructor
@Setter
@Getter
@Builder
@EqualsAndHashCode
public class PassportDto {
    private Long id;
    @NotNull
    @Min(value = 1000, message = "Номер серии паспорта должен состоять из 4 цифр")
    @Max(value = 9999, message = "Номер серии паспорта должен состоять из 4 цифр")
    private Integer series;
    @NotNull
    @Min(value = 100000L, message = "Номер паспорта должен состоять из 6 цифр")
    @Max(value = 999999L, message = "Номер паспорта должен состоять из 6 цифр")
    private Long number;
    @NotNull
    @NotBlank
    @Size(max = 255, message = "Допустимое число символов в поле \"lastName\" = 255")
    private String lastName;
    @NotNull
    @NotBlank
    @Size(max = 255, message = "Допустимое число символов в поле \"firstName\" = 255")
    private String firstName;
    @Size(max = 255, message = "Допустимое число символов в поле \"middleName\" = 255")
    private String middleName;
    @NotNull
    @NotBlank
    @Size(max = 3, message = "Допустимое число символов в поле \"gender\" = 3")
    private String gender;
    @NotNull
    private LocalDate birthDate;
    @NotNull
    @NotBlank
    @Size(max = 480, message = "Допустимое число символов в поле \"birthPlace\" = 480")
    private String birthPlace;
    @NotNull
    @NotBlank
    private String issuedBy;
    @NotNull
    private LocalDate dateOfIssue;
    @NotNull
    @Min(value = 100000, message = "Номер кода подразделения должен состоять из 6 цифр")
    @Max(value = 999999, message = "Номер кода подразделения должен состоять из 6 цифр")
    private Integer divisionCode;
    private LocalDate expirationDate;
    @NotNull
    private RegistrationDto registration;
}
