package com.bank.profile.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "profile")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "phone_number")
    @NotNull
    @NotBlank
    @Size(min = 10, max = 10)
    private Long phoneNumber;
    @Column(name = "email")
    @Size(max = 264)
    private String email;
    @Column(name = "name_on_card")
    @Size(max = 370)
    private String nameOnCard;
    @Column(name = "inn", unique = true)
    @Size(min = 12, max = 12)
    private Long inn;
    @Column(name = "snils", unique = true)
    @Size(min = 11, max = 11)
    private Long snils;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "passport_id")
    @NotNull
    @NotBlank
    private Passport passport;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true) // optional = false,
    @JoinColumn(name = "actual_registration_id")
    private ActualRegistration actualRegistration;



    // Переделать на удаленную таблицу
    @OneToMany
    @JoinTable(name = "account_details_id",
                joinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"))
    private Set<AccountDetails> accountDetailsSet = new HashSet<>();
}
