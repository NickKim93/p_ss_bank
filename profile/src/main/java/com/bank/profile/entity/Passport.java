package com.bank.profile.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "passport")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "series")
    @NotNull
    @NotBlank
    @Size(min = 4, max = 4)
    private Integer series;
    @Column(name = "number")
    @NotNull
    @NotBlank
    @Size(min = 6, max = 6)
    private Long number;
    @Column(name = "last_name")
    @NotNull
    @NotBlank
    @Size(max = 255)
    private String lastName;
    @Column(name = "first_name")
    @NotNull
    @NotBlank
    @Size(max = 255)
    private String firstName;
    @Column(name = "middle_name")
    @Size(max = 255)
    private String middleName;
    @Column(name = "gender")
    @NotNull
    @NotBlank
    @Size(max = 3)
    private String gender;
    @Column(name = "birth_date")
    @NotNull
    @NotBlank
    private LocalDate birthDate;
    @Column(name = "birth_place")
    @NotNull
    @NotBlank
    @Size(max = 480)
    private String birthPlace;
    @Column(name = "issued_by")
    @NotNull
    @NotBlank
    private String issuedBy;
    @Column(name = "date_of_issue")
    @NotNull
    @NotBlank
    private LocalDate dateOfIssue;
    @Column(name = "division_code")
    @NotNull
    @NotBlank
    @Size(min = 6, max = 6)
    private Integer divisionCode;
    @Column(name = "expiration_date")
    private LocalDate expirationDate;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "registration_id")
    @NotNull
    @NotBlank
    private Registration registration;
    @OneToOne(mappedBy = "passport")
    private Profile profile;
}
