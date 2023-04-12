package com.bank.antifraud.service;

import com.bank.antifraud.dto.SuspiciousPhoneTransfersDto;
import com.bank.antifraud.entity.SuspiciousPhoneTransfersEntity;
import com.bank.antifraud.repository.SuspiciousPhoneTransfersRepository;
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
class SuspiciousPhoneTransfersServiceImplTest {

    @Mock
    private SuspiciousPhoneTransfersRepository mockRepository;
    @InjectMocks
    private SuspiciousPhoneTransfersServiceImpl suspiciousPhoneTransfersService;

    @AfterEach
    void after() {
        Mockito.reset(mockRepository);
    }

    @Test
    void save_mustCallSaveMethod() {
        suspiciousPhoneTransfersService.save(Mockito.any());

        Mockito.verify(mockRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void findAll_mustReturnEmptyList_whenAuditCountIs0() {
        Mockito.when(mockRepository.findAll()).thenReturn(List.of());

        List<SuspiciousPhoneTransfersEntity> result = suspiciousPhoneTransfersService.findAll();

        assertTrue(result.isEmpty());
    }

    @Test
    void findById_mustReturnAuditById_whenExist() {
        SuspiciousPhoneTransfersEntity auditEntity = new SuspiciousPhoneTransfersEntity();
        auditEntity.setId(BigInteger.ONE);

        Mockito.when(mockRepository.findById(BigInteger.ONE)).thenReturn(Optional.of(auditEntity));

        SuspiciousPhoneTransfersEntity result = suspiciousPhoneTransfersService.findById(BigInteger.ONE);

        Mockito.verify(mockRepository, Mockito.times(1)).findById(Mockito.any());
        assertEquals(result, auditEntity);
    }

    @Test
    void findById_mustEntityNotFoundException_whenNotExist() {
        Mockito.when(mockRepository.findById(BigInteger.ONE)).thenReturn(Optional.empty());

        try {
            suspiciousPhoneTransfersService.findById(BigInteger.ONE);
            fail("Expected EntityNotFoundException but no exception was thrown");
        } catch (EntityNotFoundException ignored) {

        }
    }

    @Test
    void delete_mustEntityNotFoundException_whenNotExist() {
        Mockito.when(mockRepository.findById(BigInteger.ONE)).thenReturn(Optional.empty());

        try {
            suspiciousPhoneTransfersService.delete(BigInteger.ONE);
            fail("Expected EntityNotFoundException but no exception was thrown");
        } catch (EntityNotFoundException ignored) {

        }
    }

    @Test
    void delete_mustCallDelete_whenExist() {
        SuspiciousPhoneTransfersEntity auditEntity = new SuspiciousPhoneTransfersEntity();
        Mockito.when(mockRepository.findById(BigInteger.ONE)).thenReturn(Optional.of(auditEntity));

        suspiciousPhoneTransfersService.delete(BigInteger.ONE);

        Mockito.verify(mockRepository, Mockito.times(1)).delete(auditEntity);
    }

    @Test
    void update_mustCallFindByIdAndSaveInRepository_whenExist() {
        Mockito.when(mockRepository.findById(BigInteger.ONE)).thenReturn(Optional.of(new SuspiciousPhoneTransfersEntity()));
        SuspiciousPhoneTransfersDto dto = new SuspiciousPhoneTransfersDto(
                BigInteger.ONE,
                1L,
                null,
                null,
                null,
                null
        );

        suspiciousPhoneTransfersService.update(dto);

        Mockito.verify(mockRepository, Mockito.times(1)).findById(Mockito.any());
        Mockito.verify(mockRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void update_mustEntityNotFoundException_whenNotExist() {
        Mockito.when(mockRepository.findById(BigInteger.ONE)).thenReturn(Optional.empty());
        SuspiciousPhoneTransfersDto dto = new SuspiciousPhoneTransfersDto(
                BigInteger.ONE,
                1L,
                null,
                null,
                null,
                null
        );

        try {
            suspiciousPhoneTransfersService.update(dto);
            fail();
        } catch (EntityNotFoundException ignored) {

        }
    }
}