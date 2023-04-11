package com.bank.antifraud.service;

import com.bank.antifraud.dto.SuspiciousAccountTransfersDto;
import com.bank.antifraud.dto.SuspiciousCardTransferDto;
import com.bank.antifraud.entity.SuspiciousAccountTransfersEntity;
import com.bank.antifraud.entity.SuspiciousCardTransferEntity;
import com.bank.antifraud.repository.SuspiciousCardTransferRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class SuspiciousCardTransferServiceImplTest {

    @Mock
    private SuspiciousCardTransferRepository mockRepository;
    @InjectMocks
    private SuspiciousCardTransferServiceImpl suspiciousCardTransferService;

    @AfterEach
    void after() {
        Mockito.reset(mockRepository);
    }

    @Test
    void save_mustCallSaveMethod() {
        suspiciousCardTransferService.save(Mockito.any());

        Mockito.verify(mockRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void findAll_mustReturnEmptyList_whenAuditCountIs0() {
        Mockito.when(mockRepository.findAll()).thenReturn(List.of());

        List<SuspiciousCardTransferEntity> result = suspiciousCardTransferService.findAll();

        assertTrue(result.isEmpty());
    }

    @Test
    void findById_mustReturnAuditById_whenExist() {
        SuspiciousCardTransferEntity auditEntity = new SuspiciousCardTransferEntity();
        auditEntity.setId(BigInteger.ONE);

        Mockito.when(mockRepository.findById(BigInteger.ONE)).thenReturn(Optional.of(auditEntity));

        SuspiciousCardTransferEntity result = suspiciousCardTransferService.findById(BigInteger.ONE);

        Mockito.verify(mockRepository, Mockito.times(1)).findById(Mockito.any());
        assertEquals(result, auditEntity);
    }

    @Test
    void findById_mustEntityNotFoundException_whenNotExist() {
        Mockito.when(mockRepository.findById(BigInteger.ONE)).thenReturn(Optional.empty());

        try {
            suspiciousCardTransferService.findById(BigInteger.ONE);
            fail("Expected EntityNotFoundException but no exception was thrown");
        } catch (EntityNotFoundException ignored) {

        }
    }

    @Test
    void delete_mustEntityNotFoundException_whenNotExist() {
        Mockito.when(mockRepository.findById(BigInteger.ONE)).thenReturn(Optional.empty());

        try {
            suspiciousCardTransferService.delete(BigInteger.ONE);
            fail("Expected EntityNotFoundException but no exception was thrown");
        } catch (EntityNotFoundException ignored) {

        }
    }

    @Test
    void delete_mustCallDelete_whenExist() {
        SuspiciousCardTransferEntity auditEntity = new SuspiciousCardTransferEntity();
        Mockito.when(mockRepository.findById(BigInteger.ONE)).thenReturn(Optional.of(auditEntity));

        suspiciousCardTransferService.delete(BigInteger.ONE);

        Mockito.verify(mockRepository, Mockito.times(1)).delete(auditEntity);
    }

    @Test
    void update_mustCallFindByIdAndSaveInRepository_whenExist() {
        Mockito.when(mockRepository.findById(BigInteger.ONE)).thenReturn(Optional.of(new SuspiciousCardTransferEntity()));
        SuspiciousCardTransferDto dto = new SuspiciousCardTransferDto(
                BigInteger.ONE,
                1L,
                null,
                null,
                null,
                null
        );

        suspiciousCardTransferService.update(dto);

        Mockito.verify(mockRepository, Mockito.times(1)).findById(Mockito.any());
        Mockito.verify(mockRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void update_mustEntityNotFoundException_whenNotExist() {
        Mockito.when(mockRepository.findById(BigInteger.ONE)).thenReturn(Optional.empty());
        SuspiciousCardTransferDto dto = new SuspiciousCardTransferDto(
                BigInteger.ONE,
                1L,
                null,
                null,
                null,
                null
        );

        try {
            suspiciousCardTransferService.update(dto);
            fail();
        } catch (EntityNotFoundException ignored) {

        }
    }
}