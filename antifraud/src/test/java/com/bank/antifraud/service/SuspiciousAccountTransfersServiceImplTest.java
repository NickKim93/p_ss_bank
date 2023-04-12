package com.bank.antifraud.service;

import com.bank.antifraud.dto.SuspiciousAccountTransfersDto;
import com.bank.antifraud.entity.SuspiciousAccountTransfersEntity;
import com.bank.antifraud.repository.SuspiciousAccountTransfersRepository;
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
class SuspiciousAccountTransfersServiceImplTest {

    @Mock
    private SuspiciousAccountTransfersRepository mockRepository;
    @InjectMocks
    private SuspiciousAccountTransfersServiceImpl suspiciousAccountTransfersService;

    @AfterEach
    void after() {
        Mockito.reset(mockRepository);
    }

    @Test
    void save_mustCallSaveMethod() {
        suspiciousAccountTransfersService.save(Mockito.any());

        Mockito.verify(mockRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void findAll_mustReturnEmptyList_whenAuditCountIs0() {
        Mockito.when(mockRepository.findAll()).thenReturn(List.of());

        List<SuspiciousAccountTransfersEntity> result = suspiciousAccountTransfersService.findAll();

        assertTrue(result.isEmpty());
    }

    @Test
    void findById_mustReturnAuditById_whenExist() {
        SuspiciousAccountTransfersEntity auditEntity = new SuspiciousAccountTransfersEntity();
        auditEntity.setId(BigInteger.ONE);

        Mockito.when(mockRepository.findById(BigInteger.ONE)).thenReturn(Optional.of(auditEntity));

        SuspiciousAccountTransfersEntity result = suspiciousAccountTransfersService.findById(BigInteger.ONE);

        Mockito.verify(mockRepository, Mockito.times(1)).findById(Mockito.any());
        assertEquals(result, auditEntity);
    }

    @Test
    void findById_mustEntityNotFoundException_whenNotExist() {
        Mockito.when(mockRepository.findById(BigInteger.ONE)).thenReturn(Optional.empty());

        try {
            suspiciousAccountTransfersService.findById(BigInteger.ONE);
            fail("Expected EntityNotFoundException but no exception was thrown");
        } catch (EntityNotFoundException ignored) {

        }
    }

    @Test
    void delete_mustEntityNotFoundException_whenNotExist() {
        Mockito.when(mockRepository.findById(BigInteger.ONE)).thenReturn(Optional.empty());

        try {
            suspiciousAccountTransfersService.delete(BigInteger.ONE);
            fail("Expected EntityNotFoundException but no exception was thrown");
        } catch (EntityNotFoundException ignored) {

        }
    }

    @Test
    void delete_mustCallDelete_whenExist() {
        SuspiciousAccountTransfersEntity auditEntity = new SuspiciousAccountTransfersEntity();
        Mockito.when(mockRepository.findById(BigInteger.ONE)).thenReturn(Optional.of(auditEntity));

        suspiciousAccountTransfersService.delete(BigInteger.ONE);

        Mockito.verify(mockRepository, Mockito.times(1)).delete(auditEntity);
    }

    @Test
    void update_mustCallFindByIdAndSaveInRepository_whenExist() {
        Mockito.when(mockRepository.findById(BigInteger.ONE)).thenReturn(Optional.of(new SuspiciousAccountTransfersEntity()));
        SuspiciousAccountTransfersDto dto = new SuspiciousAccountTransfersDto(
                BigInteger.ONE,
                1L,
                null,
                null,
                null,
                null
        );

        suspiciousAccountTransfersService.update(dto);

        Mockito.verify(mockRepository, Mockito.times(1)).findById(Mockito.any());
        Mockito.verify(mockRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void update_mustEntityNotFoundException_whenNotExist() {
        Mockito.when(mockRepository.findById(BigInteger.ONE)).thenReturn(Optional.empty());
        SuspiciousAccountTransfersDto dto = new SuspiciousAccountTransfersDto(
                BigInteger.ONE,
                1L,
                null,
                null,
                null,
                null
        );

        try {
            suspiciousAccountTransfersService.update(dto);
            fail();
        } catch (EntityNotFoundException ignored) {

        }
    }
}