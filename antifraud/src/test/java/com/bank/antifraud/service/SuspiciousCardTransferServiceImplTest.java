package com.bank.antifraud.service;

import com.bank.antifraud.dto.SuspiciousCardTransferDto;
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
        auditEntity.setId(1L);

        Mockito.when(mockRepository.findById(1L)).thenReturn(Optional.of(auditEntity));

        SuspiciousCardTransferEntity result = suspiciousCardTransferService.findById(1L);

        Mockito.verify(mockRepository, Mockito.times(1)).findById(Mockito.any());
        assertEquals(result, auditEntity);
    }

    @Test
    void findById_mustEntityNotFoundException_whenNotExist() {
        Mockito.when(mockRepository.findById(1L)).thenReturn(Optional.empty());

        try {
            suspiciousCardTransferService.findById(1L);
            fail("Expected EntityNotFoundException but no exception was thrown");
        } catch (EntityNotFoundException ignored) {

        }
    }

    @Test
    void delete_mustEntityNotFoundException_whenNotExist() {
        Mockito.when(mockRepository.findById(1L)).thenReturn(Optional.empty());

        try {
            suspiciousCardTransferService.delete(1L);
            fail("Expected EntityNotFoundException but no exception was thrown");
        } catch (EntityNotFoundException ignored) {

        }
    }

    @Test
    void delete_mustCallDelete_whenExist() {
        SuspiciousCardTransferEntity auditEntity = new SuspiciousCardTransferEntity();
        Mockito.when(mockRepository.findById(1L)).thenReturn(Optional.of(auditEntity));

        suspiciousCardTransferService.delete(1L);

        Mockito.verify(mockRepository, Mockito.times(1)).delete(auditEntity);
    }

    @Test
    void update_mustCallFindByIdAndSaveInRepository_whenExist() {
        Mockito.when(mockRepository.findById(1L)).thenReturn(Optional.of(new SuspiciousCardTransferEntity()));
        SuspiciousCardTransferDto dto = new SuspiciousCardTransferDto(
                1L,
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
        Mockito.when(mockRepository.findById(1L)).thenReturn(Optional.empty());
        SuspiciousCardTransferDto dto = new SuspiciousCardTransferDto(
                1L,
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