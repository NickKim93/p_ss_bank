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
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

        verify(mockRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void findAll_mustReturnEmptyList_whenAuditCountIs0() {
        when(mockRepository.findAll()).thenReturn(List.of());

        List<SuspiciousAccountTransfersEntity> result = suspiciousAccountTransfersService.findAll();

        assertTrue(result.isEmpty());
    }

    @Test
    void findById_mustReturnAuditById_whenExist() {
        SuspiciousAccountTransfersEntity auditEntity = new SuspiciousAccountTransfersEntity();
        auditEntity.setId(1L);

        when(mockRepository.findById(1L)).thenReturn(Optional.of(auditEntity));

        SuspiciousAccountTransfersEntity result = suspiciousAccountTransfersService.findById(1L);

        verify(mockRepository, Mockito.times(1)).findById(Mockito.any());
        assertEquals(result, auditEntity);
    }

    @Test
    void findById_mustEntityNotFoundException_whenNotExist() {
        when(mockRepository.findById(1L)).thenReturn(Optional.empty());

        try {
            suspiciousAccountTransfersService.findById(1L);
            fail("Expected EntityNotFoundException but no exception was thrown");
        } catch (EntityNotFoundException ignored) {

        }
    }

    @Test
    void delete_mustEntityNotFoundException_whenNotExist() {
        when(mockRepository.findById(1L)).thenReturn(Optional.empty());

        try {
            suspiciousAccountTransfersService.delete(1L);
            fail("Expected EntityNotFoundException but no exception was thrown");
        } catch (EntityNotFoundException ignored) {

        }
    }

    @Test
    void delete_mustCallDelete_whenExist() {
        SuspiciousAccountTransfersEntity auditEntity = new SuspiciousAccountTransfersEntity();
        when(mockRepository.findById(1L)).thenReturn(Optional.of(auditEntity));

        suspiciousAccountTransfersService.delete(1L);

        verify(mockRepository, Mockito.times(1)).delete(auditEntity);
    }

    @Test
    void update_mustCallFindByIdAndSaveInRepository_whenExist() {
        when(mockRepository.findById(1L)).thenReturn(Optional.of(new SuspiciousAccountTransfersEntity()));
        SuspiciousAccountTransfersDto dto = new SuspiciousAccountTransfersDto(
                1L,
                1L,
                null,
                null,
                null,
                null
        );

        suspiciousAccountTransfersService.update(dto);

        verify(mockRepository, Mockito.times(1)).findById(Mockito.any());
        verify(mockRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void update_mustEntityNotFoundException_whenNotExist() {
        when(mockRepository.findById(1L)).thenReturn(Optional.empty());
        SuspiciousAccountTransfersDto dto = new SuspiciousAccountTransfersDto(
                1L,
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