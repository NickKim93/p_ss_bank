package com.bank.account.entity;

import com.bank.account.entity.AccountDetails;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.springframework.test.util.AssertionErrors.assertEquals;
class AccountDetailsTest {

    AccountDetails details = new AccountDetails(1L, 1L, 1L, 1L, BigInteger.valueOf(220L), false, 1L);

    @Test
    void getId() {
        assertEquals("id не совпали", 1L, details.getId());
    }

    @Test
    void getPassportId() {
        assertEquals("Passport id не совпали", 1L, details.getPassportId());

    }

    @Test
    void getAccountNumber() {
        assertEquals("account number не совпали", 1L, details.getAccountNumber());

    }

    @Test
    void getBankDetailsId() {
        assertEquals("bank details id не совпали", 1L, details.getBankDetailsId());
    }

    @Test
    void getMoney() {
        assertEquals("money не совпали", BigInteger.valueOf(220L), details.getMoney());
    }

    @Test
    void getNegativeBalance() {
        assertEquals("negative balance не совпали", false, details.getNegativeBalance());

    }

    @Test
    void getProfileId() {
        assertEquals("profile id не совпали", 1L, details.getProfileId());
    }

    @Test
    void setId() {
        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setId(1L);
        assertEquals("setId отработал не верно", 1L, accountDetails.getId());

    }

    @Test
    void setPassportId() {
        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setPassportId(1L);
        assertEquals("setPassportId отработал не верно", 1L, accountDetails.getPassportId());

    }

    @Test
    void setAccountNumber() {
        AccountDetails accountDetails = new AccountDetails();;
        accountDetails.setAccountNumber(1L);
        assertEquals("setAccountNumber отработал не верно", 1L, accountDetails.getAccountNumber());

    }

    @Test
    void setBankDetailsId() {
        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setBankDetailsId(1L);
        assertEquals("setBankDetailsId отработал не верно", 1L, accountDetails.getBankDetailsId());

    }

    @Test
    void setMoney() {
        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setMoney(BigInteger.valueOf(200L));
        assertEquals("setMoney отработал не верно", BigInteger.valueOf(200L), accountDetails.getMoney());

    }

    @Test
    void setNegativeBalance() {
        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setNegativeBalance(true);
        assertEquals("setNegativeBalance отработал не верно", true, accountDetails.getNegativeBalance());

    }

    @Test
    void setProfileId() {
        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setProfileId(1L);
        assertEquals("setProfileId отработал не верно", 1L, accountDetails.getProfileId());

    }
}