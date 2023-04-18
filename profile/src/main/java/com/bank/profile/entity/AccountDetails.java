package com.bank.profile.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Тестовая сущность удаленного микросервиса account
 */
@Entity
@Table(name = "account_details")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
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
    @JsonBackReference
    private Long profileId;
}
