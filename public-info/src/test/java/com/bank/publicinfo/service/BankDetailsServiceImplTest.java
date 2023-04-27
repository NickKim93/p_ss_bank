package com.bank.publicinfo.service;

import com.bank.publicinfo.dto.BankDetailsDto;
import com.bank.publicinfo.entity.BankDetails;
import com.bank.publicinfo.mapper.BankDetailsMapper;
import com.bank.publicinfo.repository.BankDetailsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BankDetailsServiceImplTest {

    @Mock
    private BankDetailsRepository bankDetailsRepository;

    @Mock
    private BankDetailsMapper bankDetailsMapper;
    @InjectMocks
    private BankDetailsServiceImpl bankDetailsService;

    private BankDetails bankDetails;

    private BankDetailsDto bankDetailsDto;

    @BeforeEach
    public void setup() {
        bankDetails = new BankDetails(1L,
                BigInteger.valueOf(123),
                BigInteger.valueOf(123),
                BigInteger.valueOf(123),
                123,
                "Moscow", "OOO", "OOO", null, null);
        bankDetailsDto = new BankDetailsDto(1L,
                BigInteger.valueOf(123),
                BigInteger.valueOf(123),
                BigInteger.valueOf(123),
                123,
                "Moscow", "OOO", "OOO", null, null);
    }

    @Test
    void getBankDetailsByIdTest() {
        // given
        when(bankDetailsRepository.findById(1L)).thenReturn(Optional.of(bankDetails));
        when(bankDetailsMapper.bankDetailsEntityToDto(any(BankDetails.class))).thenReturn(bankDetailsDto);

        // when
        BankDetailsDto result = bankDetailsService.getBankDetailsById(1L);

        // then
        assertEquals(result, bankDetailsDto);
    }

    @Test
    void getBankDetailsByIdNotFoundExceptionTest() {
        // given
        Long id = 1L;
        when(bankDetailsRepository.findById(id)).thenReturn(Optional.empty());
        // when and then
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class
                , () -> bankDetailsService.getBankDetailsById(id));
        assertEquals("BankDetails not found", exception.getMessage());
        verify(bankDetailsRepository, times(1)).findById(id);
        verifyNoMoreInteractions(bankDetailsRepository, bankDetailsMapper);
    }



    @Test
    void getAllBankDetailsTest() {

        // given
        List<BankDetails> bankDetailsList = new ArrayList<>();
        bankDetailsList.add(bankDetails);
        when(bankDetailsRepository.findAll()).thenReturn(bankDetailsList);
        when(bankDetailsMapper.bankDetailsListEntityToDto(any(List.class))).thenReturn(List.of(bankDetailsDto));
        // when
        List<BankDetailsDto> result = bankDetailsService.getAllBankDetails();
        // then
        assertEquals(result, List.of(bankDetailsDto));
    }

    @Test
    void createBankDetailsTest() {
        // given
        when(bankDetailsMapper.bankDetailsDtoToEntity(any(BankDetailsDto.class))).thenReturn(bankDetails);
        when(bankDetailsRepository.save(any(BankDetails.class))).thenReturn(bankDetails);
        when(bankDetailsMapper.bankDetailsEntityToDto(any(BankDetails.class))).thenReturn(bankDetailsDto);
        // when
        BankDetailsDto result = bankDetailsService.createBankDetails(bankDetailsDto);
        // then
        assertEquals(result, bankDetailsDto);
    }

    @Test
    void updateBankDetailsTest() {

        // given

        BankDetailsDto updatedBankDetails = new BankDetailsDto(1L,
                BigInteger.valueOf(123),
                BigInteger.valueOf(123),
                BigInteger.valueOf(123),
                123,
                "Moscow", "OOO", "OOO", null, null);
        when(bankDetailsRepository.findById(1L)).thenReturn(Optional.of(bankDetails));
        when(bankDetailsRepository.save(any(BankDetails.class))).thenReturn(bankDetails);
        when(bankDetailsMapper.bankDetailsEntityToDto(any(BankDetails.class))).thenReturn(updatedBankDetails);
        // when

        BankDetailsDto result = bankDetailsService.updateBankDetails(1L, updatedBankDetails);

        // then
        assertEquals(result, updatedBankDetails);

    }

    @Test
    void updateBankDetailsNotFoundExceptionTest() {
        // given
        Long id = 1L;
        BankDetailsDto bankDetailsDto1 = new BankDetailsDto(1L,
                BigInteger.valueOf(123),
                BigInteger.valueOf(123),
                BigInteger.valueOf(123),
                123,
                "Moscow", "OOO", "OOO", null, null);
        when(bankDetailsRepository.findById(id)).thenReturn(Optional.empty());

        // when and then
        assertThrows(EntityNotFoundException.class, () -> bankDetailsService.updateBankDetails(id, bankDetailsDto1));
    }

    @Test
    void deleteBankDetailsByIdTest() {
        // given
        Long id = 1L;
        BankDetails bankDetails1 = new BankDetails();
        when(bankDetailsRepository.findById(id)).thenReturn(Optional.of(bankDetails1));
        // when
        bankDetailsService.deleteBankDetailsById(id);
        // then
        verify(bankDetailsRepository, times(1)).delete(bankDetails1);
    }

    @Test
    void deleteAtmNotFoundExceptionTest() {
        // given
        Long id = 1L;
        when(bankDetailsRepository.findById(id)).thenReturn(Optional.empty());

        // when and then
        assertThrows(EntityNotFoundException.class, () -> bankDetailsService.deleteBankDetailsById(id));
    }
}