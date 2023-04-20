package com.bank.account.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigInteger;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDetailsDTO {
    private Long id;
    @NotNull
    @Positive
    private Long passportId;
    @NotNull
    @Positive
    private Long accountNumber;
    @NotNull
    @Positive
    private Long bankDetailsId;
    @NotNull
    @Positive
    private BigInteger money;
    @NotNull
    private Boolean negativeBalance;
    @NotNull
    @Positive
    private Long profileId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountDetailsDTO that = (AccountDetailsDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(passportId, that.passportId) &&
                Objects.equals(accountNumber, that.accountNumber) &&
                Objects.equals(bankDetailsId, that.bankDetailsId) &&
                Objects.equals(money, that.money) &&
                Objects.equals(negativeBalance, that.negativeBalance) &&
                Objects.equals(profileId, that.profileId);
    }
}


