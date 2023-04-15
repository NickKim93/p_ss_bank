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
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

        verify(mockRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void findAll_mustReturnEmptyList_whenAuditCountIs0() {
        when(mockRepository.findAll()).thenReturn(List.of());

        List<SuspiciousPhoneTransfersEntity> result = suspiciousPhoneTransfersService.findAll();

        assertTrue(result.isEmpty());
    }

    @Test
    void findById_mustReturnAuditById_whenExist() {
        SuspiciousPhoneTransfersEntity auditEntity = new SuspiciousPhoneTransfersEntity();
        auditEntity.setId(1L);

        when(mockRepository.findById(1L)).thenReturn(Optional.of(auditEntity));

        SuspiciousPhoneTransfersEntity result = suspiciousPhoneTransfersService.findById(1L);

        verify(mockRepository, Mockito.times(1)).findById(Mockito.any());
        assertEquals(result, auditEntity);
    }

    @Test
    void findById_mustEntityNotFoundException_whenNotExist() {
        when(mockRepository.findById(1L)).thenReturn(Optional.empty());

        try {
            suspiciousPhoneTransfersService.findById(1L);
            fail("Expected EntityNotFoundException but no exception was thrown");
        } catch (EntityNotFoundException ignored) {

        }
    }

    @Test
    void delete_mustEntityNotFoundException_whenNotExist() {
        when(mockRepository.findById(1L)).thenReturn(Optional.empty());

        try {
            suspiciousPhoneTransfersService.delete(1L);
            fail("Expected EntityNotFoundException but no exception was thrown");
        } catch (EntityNotFoundException ignored) {

        }
    }

    @Test
    void delete_mustCallDelete_whenExist() {
        SuspiciousPhoneTransfersEntity auditEntity = new SuspiciousPhoneTransfersEntity();
        when(mockRepository.findById(1L)).thenReturn(Optional.of(auditEntity));

        suspiciousPhoneTransfersService.delete(1L);

        verify(mockRepository, Mockito.times(1)).delete(auditEntity);
    }

    @Test
    void update_mustCallFindByIdAndSaveInRepository_whenExist() {
        when(mockRepository.findById(1L)).thenReturn(Optional.of(new SuspiciousPhoneTransfersEntity()));
        SuspiciousPhoneTransfersDto dto = new SuspiciousPhoneTransfersDto(
                1L,
                1L,
                null,
                null,
                null,
                null
        );

        suspiciousPhoneTransfersService.update(dto);

        verify(mockRepository, Mockito.times(1)).findById(Mockito.any());
        verify(mockRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void update_mustEntityNotFoundException_whenNotExist() {
        when(mockRepository.findById(1L)).thenReturn(Optional.empty());
        SuspiciousPhoneTransfersDto dto = new SuspiciousPhoneTransfersDto(
                1L,
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