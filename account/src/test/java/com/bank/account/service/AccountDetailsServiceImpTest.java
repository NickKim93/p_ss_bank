package com.bank.account.service;
import com.bank.account.controller.AccountDetailsController;
import com.bank.account.dto.AccountDetailsDTO;
import com.bank.account.entity.AccountDetails;
import com.bank.account.mapper.AccountDetailsMapper;
import com.bank.account.repository.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(SpringExtension.class)
public class AccountDetailsServiceImpTest {
    @InjectMocks
    private AccountDetailsServiceImp detailsService;
    @Mock
    private AccountRepository accountRepository;
    AccountDetailsDTO detailsDTO = new AccountDetailsDTO(1L, 1L, 1L, 1L, BigInteger.valueOf(220L), false, 1L);
    AccountDetails accountDetails = new AccountDetails(1L, 1L, 1L, 1L, BigInteger.valueOf(220L), false, 1L);
    @Test
    public void shouldReturnAllAccountDetails() {
        Mockito.when(accountRepository.findAll()).thenReturn(getAllDetails());
        List<AccountDetailsDTO> result = detailsService.getAllAccountDetails();
        List<AccountDetailsDTO> listDto = getAllDetails().stream().map(AccountDetailsMapper.INSTANCE::toDTO).toList();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
        Assertions.assertSame(result.get(1).getAccountNumber(), listDto.get(1).getAccountNumber());
    }

    @Test
    public void shouldReturnAccountDetailsById() {
        AccountDetails firstDetails = new AccountDetails(1L, 1L, 1L, 1L, new BigInteger("220"), false, 1L);
        Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.ofNullable(getAllDetails().get(0)));
        AccountDetailsDTO detailsDTO = detailsService.getAccountDetails(1L);
        Assertions.assertEquals(detailsDTO, AccountDetailsMapper.INSTANCE.toDTO(firstDetails));
    }

    @Test
    public void shouldSaveToRepository() {
        detailsService.save(Mockito.any());
        verify(accountRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void shouldDeleteToRepository() {
        detailsService.deleteById(Mockito.any());
        verify(accountRepository, Mockito.times(1)).deleteById(Mockito.any());
    }

    @Test
    public void shouldUpdateEntity() {
        when(accountRepository.findAccountDetailsByAccountNumber(detailsDTO.getAccountNumber()))
                .thenReturn(Optional.of(accountDetails));
        detailsService.updateAccountDetails(detailsDTO);
        verify(accountRepository, Mockito.times(1)).findAccountDetailsByAccountNumber(Mockito.any());
        verify(accountRepository, Mockito.times(1)).save(Mockito.any());
    }

    private List<AccountDetails> getAllDetails() {
        AccountDetails firstDetails = new AccountDetails(1L, 1L, 1L, 1L, new BigInteger("220"), false, 1L);
        AccountDetails secondDetails = new AccountDetails(2L, 2L, 2L, 2L, new BigInteger("230"), false, 2L);
        return List.of(firstDetails, secondDetails);
    }
}
