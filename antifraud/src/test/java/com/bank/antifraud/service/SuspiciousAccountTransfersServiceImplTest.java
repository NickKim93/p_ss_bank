package com.bank.antifraud.service;

import com.bank.antifraud.dto.SuspiciousTransferDto;
import com.bank.antifraud.entity.AccountEntity;
import com.bank.antifraud.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@DisplayName("Бизнес логика подозрительных транзакций (account)")
class SuspiciousAccountTransfersServiceImplTest {

    @Mock
    private AccountRepository mockRepository;
    @InjectMocks
    private AccountServiceImpl suspiciousAccountTransfersService;

    SuspiciousTransferDto dto;

    @BeforeEach
    void setUp() {
        dto = new SuspiciousTransferDto(
                "account",
                1L,
                1L,
                null,
                null,
                null,
                null
        );
    }


    @Test
    @DisplayName("Метод save должен вызвать Repository.save()")
    void save_mustCallSaveMethod() {
        // given

        // when
        suspiciousAccountTransfersService.save(dto);

        // then
        verify(mockRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("findAll должен вернуть пустой лист когда нет сущностей")
    void findAll_mustReturnEmptyList_whenAuditCountIs0() {
        // given
        when(mockRepository.findAll()).thenReturn(List.of());

        // when
        List<AccountEntity> result = suspiciousAccountTransfersService.findAll();

        // then
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("findById должен вернуть сущность, когда она существует")
    void findById_mustReturnAuditById_whenExist() {
        // given
        AccountEntity auditEntity = new AccountEntity();
        auditEntity.setId(1L);
        when(mockRepository.findById(1L)).thenReturn(Optional.of(auditEntity));

        // when
        AccountEntity result = suspiciousAccountTransfersService.findById(1L);

        // then
        verify(mockRepository, times(1)).findById(any());
        assertEquals(result, auditEntity);
    }

    @Test
    @DisplayName("findById должен выбросить исключение, когда сущности не существует")
    void findById_mustEntityNotFoundException_whenNotExist() {
        // given
        when(mockRepository.findById(1L)).thenReturn(Optional.empty());

        // when&then
        try {
            suspiciousAccountTransfersService.findById(1L);
            fail("Expected EntityNotFoundException but no exception was thrown");
        } catch (EntityNotFoundException ignored) {

        }
    }

    @Test
    @DisplayName("delete должен выбросить исключение, когда сущности не существует")
    void delete_mustEntityNotFoundException_whenNotExist() {
        // given
        when(mockRepository.findById(1L)).thenReturn(Optional.empty());

        // when&then
        try {
            suspiciousAccountTransfersService.delete(1L);
            fail("Expected EntityNotFoundException but no exception was thrown");
        } catch (EntityNotFoundException ignored) {

        }
    }

    @Test
    @DisplayName("delete должен вызвать Repository.delete() когда сущность с id существует")
    void delete_mustCallDelete_whenExist() {
        // given
        AccountEntity auditEntity = new AccountEntity();
        when(mockRepository.findById(1L)).thenReturn(Optional.of(auditEntity));

        // when
        suspiciousAccountTransfersService.delete(1L);

        // then
        verify(mockRepository, times(1)).delete(auditEntity);
    }

    @Test
    @DisplayName("update должен вызвать Repository.findById() и Repository.save() когда сущность с id существует")
    void update_mustCallFindByIdAndSaveInRepository_whenExist() {
        // given
        when(mockRepository.findById(1L)).thenReturn(Optional.of(new AccountEntity()));

        // when
        suspiciousAccountTransfersService.update(dto);

        // then
        verify(mockRepository, times(1)).findById(any());
        verify(mockRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("update должен выбросить исключение, когда сущность с id не существует")
    void update_mustEntityNotFoundException_whenNotExist() {
        // given
        when(mockRepository.findById(1L)).thenReturn(Optional.empty());

        // when&then
        try {
            suspiciousAccountTransfersService.update(dto);
            fail();
        } catch (EntityNotFoundException ignored) {

        }
    }
}