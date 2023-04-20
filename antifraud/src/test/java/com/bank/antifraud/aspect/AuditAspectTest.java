package com.bank.antifraud.aspect;

import com.bank.antifraud.entity.AccountEntity;
import com.bank.antifraud.entity.AuditEntity;
import com.bank.antifraud.entity.SuspiciousTransfer;
import com.bank.antifraud.repository.AuditRepository;
import com.bank.antifraud.util.OperationType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@DisplayName("Аудит")
class AuditAspectTest {
    @Mock
    private AuditRepository auditRepository;

    @InjectMocks
    private AuditAspect auditAspect;

    @Test
    @DisplayName("Аспект при операции SAVE вызывает метод Save в AuditRepository")
    public void testSave_createOperation() throws JsonProcessingException {
        // given
        Auditing auditing = mock(Auditing.class);
        when(auditing.operationType()).thenReturn(OperationType.CREATE);
        SuspiciousTransfer auditable = new AccountEntity();
        auditable.setId(1L);

        // when
        auditAspect.doAfterSaveAndUpdate(auditing, auditable);
        ArgumentCaptor<AuditEntity> argument = ArgumentCaptor.forClass(AuditEntity.class);

        // then
        verify(auditRepository).save(argument.capture());
        AuditEntity capturedEntity = argument.getValue();
        assertEquals("CREATE", capturedEntity.getOperationType());
        assertEquals("transfer", capturedEntity.getEntityType());
        assertEquals("system", capturedEntity.getCreatedBy());
        assertNotNull(capturedEntity.getCreatedAt());
    }

    @Test
    @DisplayName("Аспект при операции UPDATE вызывает метод Save в AuditRepository")
    public void testSave_updateOperation() throws JsonProcessingException {
        // given
        Auditing auditing = mock(Auditing.class);
        when(auditing.operationType()).thenReturn(OperationType.UPDATE);
        SuspiciousTransfer auditable = new AccountEntity();
        auditable.setId(1L);
        AuditEntity firstAudit = new AuditEntity();
        firstAudit.setId(auditable.getId());
        firstAudit.setEntityJson(new ObjectMapper().writeValueAsString(auditable));
        firstAudit.setCreatedAt(OffsetDateTime.now());

        // when
        when(auditRepository.findByEntityId(auditable.getId())).thenReturn(firstAudit);
        auditAspect.doAfterSaveAndUpdate(auditing, auditable);
        ArgumentCaptor<AuditEntity> argument = ArgumentCaptor.forClass(AuditEntity.class);

        //then
        verify(auditRepository).save(argument.capture());
        AuditEntity capturedEntity = argument.getValue();
        assertEquals("UPDATE", capturedEntity.getOperationType());
        assertEquals("transfer", capturedEntity.getEntityType());
        assertEquals("system", capturedEntity.getModifiedBy());
        assertNotNull(capturedEntity.getModifiedAt());
        assertEquals(firstAudit.getEntityJson(), capturedEntity.getEntityJson());
    }
}