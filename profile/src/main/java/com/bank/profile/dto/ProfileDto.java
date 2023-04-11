package com.bank.profile.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Set;

@AllArgsConstructor
@Setter
@Getter
@Builder
public class ProfileDto {
    private Long id;
    private Long phoneNumber;
    @Size(max = 264)
    @Email(message = "The email should look like 'email@mail.com'")
    private String email;
    private String nameOnCard;
    private Long inn;
    private Long snils;
    private PassportDto passport;
    private ActualRegistrationDto actualRegistration;
    private Set<AccountDetailsDto> accountDetailsSet;

}
