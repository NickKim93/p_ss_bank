package com.bank.profile.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * Сущность паспорта
 */
@Entity
@Table(name = "passport")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@EqualsAndHashCode
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "series")
    @NotNull
    @Min(value = 1000L)
    @Max(value = 9999L)
    private Integer series;

    @Column(name = "number")
    @NotNull
    @Min(value = 100000L)
    @Max(value = 999999L)
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
    private LocalDate dateOfIssue;

    @Column(name = "division_code")
    @NotNull
    @Min(value = 100000)
    @Max(value = 999999)
    private Integer divisionCode;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "registration_id")
    @NotNull
    @JsonManagedReference
    private Registration registration;

    @OneToOne(mappedBy = "passport")
    @JsonBackReference
    private Profile profile;
}
