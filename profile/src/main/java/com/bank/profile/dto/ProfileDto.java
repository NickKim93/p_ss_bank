package com.bank.profile.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@Setter
@Getter
@Builder
public class ProfileDto {
    // Раздел Profile
    private Long id;
    private Long phoneNumber;
    private String email;
    private String nameOnCard;
    private Long inn;
    private Long snils;
    private Long passportId;
    private Long actualRegistrationId;
    private Set<Long> accountDetailsId;

    // Раздел Passport
    private Integer series;
    private Long number;
    private String lastName;
    private String firstName;
    private String middleName;
    private String gender;
    private LocalDate birthDate;
    private String birthPlace;
    private String issuedBy;
    private LocalDate dateOfIssue;
    private Integer divisionCode;
    private LocalDate expirationDate;
    private Long registrationId;

    // Раздел Registration
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

    //Раздел Actual Registration
    private String countryAR;
    private String regionAR;
    private String cityAR;
    private String districtAR;
    private String localityAR;
    private String streetAR;
    private String houseNumberAR;
    private String houseBlockAR;
    private String flatNumberAR;
    private Long indexAR;
}
