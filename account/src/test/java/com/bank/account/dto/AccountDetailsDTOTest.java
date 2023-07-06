package com.bank.account.dto;

import com.bank.account.dto.AccountDetailsDTO;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.springframework.test.util.AssertionErrors.assertEquals;
class AccountDetailsDTOTest {


    AccountDetailsDTO detailsDTO = new AccountDetailsDTO(1L, 1L, 1L, 1L, BigInteger.valueOf(220L), false, 1L);

    @Test
    void getId() {
        assertEquals("id не совпали", 1L, detailsDTO.getId());
    }

    @Test
    void getPassportId() {
        assertEquals("Passport id не совпали", 1L, detailsDTO.getPassportId());

    }

    @Test
    void getAccountNumber() {
        assertEquals("account number не совпали", 1L, detailsDTO.getAccountNumber());
    }

    @Test
    void getBankDetailsId() {
        assertEquals("bank details id не совпали", 1L, detailsDTO.getBankDetailsId());
    }

    @Test
    void getMoney() {
        assertEquals("money не совпали", BigInteger.valueOf(220L), detailsDTO.getMoney());

    }

    @Test
    void getNegativeBalance() {
        assertEquals("negative balance не совпали", false, detailsDTO.getNegativeBalance());

    }

    @Test
    void getProfileId() {
        assertEquals("profile id не совпали", 1L, detailsDTO.getProfileId());

    }

    @Test
    void setId() {
        AccountDetailsDTO accountDetailsDTO = new AccountDetailsDTO();
        accountDetailsDTO.setId(1L);
        assertEquals("setId отработал не верно", 1L, accountDetailsDTO.getId());
    }

    @Test
    void setPassportId() {
        AccountDetailsDTO accountDetailsDTO = new AccountDetailsDTO();
        accountDetailsDTO.setPassportId(1L);
        assertEquals("setPassportId отработал не верно", 1L, accountDetailsDTO.getPassportId());
    }

    @Test
    void setAccountNumber() {
        AccountDetailsDTO accountDetailsDTO = new AccountDetailsDTO();
        accountDetailsDTO.setAccountNumber(1L);
        assertEquals("setAccountNumber отработал не верно", 1L, accountDetailsDTO.getAccountNumber());
    }

    @Test
    void setBankDetailsId() {
        AccountDetailsDTO accountDetailsDTO = new AccountDetailsDTO();
        accountDetailsDTO.setBankDetailsId(1L);
        assertEquals("setBankDetailsId отработал не верно", 1L, accountDetailsDTO.getBankDetailsId());
    }

    @Test
    void setMoney() {
        AccountDetailsDTO accountDetailsDTO = new AccountDetailsDTO();
        accountDetailsDTO.setMoney(BigInteger.valueOf(200L));
        assertEquals("setMoney отработал не верно", BigInteger.valueOf(200L), accountDetailsDTO.getMoney());
    }

    @Test
    void setNegativeBalance() {
        AccountDetailsDTO accountDetailsDTO = new AccountDetailsDTO();
        accountDetailsDTO.setNegativeBalance(true);
        assertEquals("setNegativeBalance отработал не верно", true, accountDetailsDTO.getNegativeBalance());
    }

    @Test
    void setProfileId() {
        AccountDetailsDTO accountDetailsDTO = new AccountDetailsDTO();
        accountDetailsDTO.setProfileId(1L);
        assertEquals("setProfileId отработал не верно", 1L, accountDetailsDTO.getProfileId());
    }
}