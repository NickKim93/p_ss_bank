package com.bank.profile.entity;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Table(name = "account_details")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AccountDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(columnDefinition = "NUMERIC(19,0)")
//    @Type(type = "org.hibernate.type.BigIntegerType")
    private BigInteger id;
    @Column(name = "passport_id")
    @NotNull
    @NotBlank
    private BigInteger passportId;
    @Column(name = "account_number", unique = true)
    @NotNull
    @NotBlank
    private BigInteger accountNumber;
    @Column(name = "bank_details_id", unique = true)
    @NotNull
    @NotBlank
    private BigInteger bankDetailsId;
    @Column(name = "money")
    @NotNull
    @NotBlank
    private BigDecimal money;
    @Column(name = "negative_balance")
    @NotNull
    @NotBlank
    private boolean negativeBalance;
    @Column(name = "profile_id")
    @NotNull
    @NotBlank
    private BigInteger profileId;
}
