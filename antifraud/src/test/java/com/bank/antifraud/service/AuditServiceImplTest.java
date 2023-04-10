package com.bank.antifraud.service;

import com.bank.antifraud.dto.AuditDto;
import com.bank.antifraud.entity.AuditEntity;
import com.bank.antifraud.mapper.AuditMapper;
import com.bank.antifraud.repository.AuditRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class AuditServiceImplTest {
    @Mock
    private AuditRepository mockRepository;
    @InjectMocks
    private AuditServiceImpl auditService;

    @AfterEach
    void after() {
        Mockito.reset(mockRepository);
    }

    @Test
    void save_mustCallSaveMethod() {
        auditService.save(Mockito.any());

        Mockito.verify(mockRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void findAll_mustReturnEmptyList_whenAuditCountIs0() {
        Mockito.when(mockRepository.findAll()).thenReturn(List.of());

        List<AuditDto> result = auditService.findAll();

        assertTrue(result.isEmpty());
    }

    @Test
    void findById_mustReturnAuditById_whenExist() {
        AuditEntity auditEntity = new AuditEntity();
        auditEntity.setId(BigInteger.ONE);

        Mockito.when(mockRepository.findById(BigInteger.ONE)).thenReturn(Optional.of(auditEntity));

        AuditDto result = auditService.findById(BigInteger.ONE);

        Mockito.verify(mockRepository, Mockito.times(1)).findById(Mockito.any());
        assertEquals(result, AuditMapper.INSTANCE.auditEntityToAuditDto(auditEntity));
    }

    @Test
    void findById_mustEntityNotFoundException_whenNotExist() {
        Mockito.when(mockRepository.findById(BigInteger.ONE)).thenReturn(Optional.empty());

        try {
            auditService.findById(BigInteger.ONE);
            fail("Expected EntityNotFoundException but no exception was thrown");
        } catch (EntityNotFoundException ignored) {

        }
    }

    @Test
    void delete_mustEntityNotFoundException_whenNotExist() {
        Mockito.when(mockRepository.findById(BigInteger.ONE)).thenReturn(Optional.empty());

        try {
            auditService.delete(BigInteger.ONE);
            fail("Expected EntityNotFoundException but no exception was thrown");
        } catch (EntityNotFoundException ignored) {

        }
    }

    @Test
    void delete_mustCallDelete_whenExist() {
        AuditEntity auditEntity = new AuditEntity();
        Mockito.when(mockRepository.findById(BigInteger.ONE)).thenReturn(Optional.of(auditEntity));

        auditService.delete(BigInteger.ONE);

        Mockito.verify(mockRepository, Mockito.times(1)).delete(auditEntity);
    }

    @Test
    void update_mustCallFindByIdAndSaveInRepository_whenExist() {
        AuditDto auditDto = new AuditDto(
                BigInteger.ONE,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
        Mockito.when(mockRepository.findById(BigInteger.ONE)).thenReturn(Optional.of(new AuditEntity()));

        auditService.update(auditDto);

        Mockito.verify(mockRepository, Mockito.times(1)).findById(Mockito.any());
        Mockito.verify(mockRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void update_mustEntityNotFoundException_whenNotExist() {
        AuditDto auditDto = new AuditDto(
                BigInteger.ONE,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
        Mockito.when(mockRepository.findById(BigInteger.ONE)).thenReturn(Optional.empty());

        try {
            auditService.update(auditDto);
            fail();
        } catch (EntityNotFoundException ignored) {

        }
    }
}