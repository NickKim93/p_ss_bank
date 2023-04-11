package com.bank.profile.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;

/**
 * Сущность профиля
 * */
@Entity
@Table(name = "profile") // , uniqueConstraints={@UniqueConstraint(columnNames = {"inn" , "snils"})}
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
    @Min(value = 1000000000L)
    @Max(value = 9999999999L)
    private Long phoneNumber;
    @Column(name = "email")
    @Size(max = 264)
    @Email(message = "The email should look like 'email@mail.com'")
    private String email;
    @Column(name = "name_on_card")
    @Size(max = 370)
    private String nameOnCard;
    @Column(name = "inn") //, unique = true
    @Min(value = 100000000000L)
    @Max(value = 999999999999L)
    private Long inn;
    @Column(name = "snils") //, unique = true
    @Min(value = 10000000000L)
    @Max(value = 99999999999L)
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
    private Set<AccountDetails> accountDetailsSet;
}
