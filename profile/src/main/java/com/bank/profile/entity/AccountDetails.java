package com.bank.profile.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Тестовая сущность удаленного микросервиса account
 * */
@Entity
@Table(name = "account_details")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AccountDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "passport_id")
    @NotNull
    private Long passportId;
    @Column(name = "account_number", unique = true)
    @NotNull
    private Long accountNumber;
    @Column(name = "bank_details_id", unique = true)
    @NotNull
    private Long bankDetailsId;
    @Column(name = "money")
    @NotNull
    private BigDecimal money;
    @Column(name = "negative_balance")
    @NotNull
    private boolean negativeBalance;
    @Column(name = "profile_id")
    @NotNull
    private Long profileId;
}
