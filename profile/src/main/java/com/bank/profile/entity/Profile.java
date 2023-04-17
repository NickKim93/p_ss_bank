package com.bank.profile.entity;

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
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Сущность профиля
 */
@Entity
@Table(name = "profile")
@SecondaryTable(name = "audit")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
public class Profile extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "phone_number")
    @NotNull
    @Min(value = 1000000000L)
    @Max(value = 9999999999L)
    private Long phoneNumber;

    @Column(name = "email")
    @Size(max = 264)
    @Email(message = "Адрес электронной почты должен иметь вид 'email@mail.com'")
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
    @JsonManagedReference
    private Passport passport;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true) // optional = false,
    @JoinColumn(name = "actual_registration_id")
    @JsonManagedReference
    private ActualRegistration actualRegistration;


    // Переделать на удаленную таблицу
    @OneToMany
    @JoinTable(name = "account_details_id",
            joinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"))
    @JsonManagedReference
    private Set<AccountDetails> accountDetailsSet;
}
