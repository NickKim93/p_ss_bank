package com.bank.antifraud.aspect;

import com.bank.antifraud.entity.AuditEntity;
import com.bank.antifraud.entity.Auditable;
import com.bank.antifraud.entity.SuspiciousAccountTransfersEntity;
import com.bank.antifraud.repository.AuditRepository;
import com.bank.antifraud.util.OperationType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class AuditAspectTest {
    @Mock
    private AuditRepository auditRepository;

    @InjectMocks
    private AuditAspect auditAspect;

    @Test
    public void testSave_createOperation() throws JsonProcessingException {
        // Arrange
        Auditing auditing = Mockito.mock(Auditing.class);
        when(auditing.operationType()).thenReturn(OperationType.CREATE);

        Auditable auditable = new SuspiciousAccountTransfersEntity();
        auditable.setId(1L);

        // Act
        auditAspect.doAfterSaveAndUpdate(auditing,
                auditable);

        // Assert
        ArgumentCaptor<AuditEntity> argument = ArgumentCaptor.forClass(AuditEntity.class);
        verify(auditRepository).save(argument.capture());

        AuditEntity capturedEntity = argument.getValue();
        assertEquals("CREATE", capturedEntity.getOperationType());
        assertEquals("transfer", capturedEntity.getEntityType());
        assertEquals("system", capturedEntity.getCreatedBy());
        assertNotNull(capturedEntity.getCreatedAt());
    }

    @Test
    public void testSave_updateOperation() throws JsonProcessingException {
        // Arrange
        Auditing auditing = Mockito.mock(Auditing.class);
        when(auditing.operationType()).thenReturn(OperationType.UPDATE);

        Auditable auditable = new SuspiciousAccountTransfersEntity();
        auditable.setId(1L);

        AuditEntity firstAudit = new AuditEntity();
        firstAudit.setId(auditable.getId());
        firstAudit.setEntityJson(new ObjectMapper().writeValueAsString(auditable));
        firstAudit.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));

        when(auditRepository.findByEntityId(auditable.getId())).thenReturn(firstAudit);

        // Act
        auditAspect.doAfterSaveAndUpdate(auditing,
                auditable);

        // Assert
        ArgumentCaptor<AuditEntity> argument = ArgumentCaptor.forClass(AuditEntity.class);
        verify(auditRepository).save(argument.capture());

        AuditEntity capturedEntity = argument.getValue();
        assertEquals("UPDATE", capturedEntity.getOperationType());
        assertEquals("transfer", capturedEntity.getEntityType());
        assertEquals("system", capturedEntity.getModifiedBy());
        assertNotNull(capturedEntity.getModifiedAt());
        assertEquals(firstAudit.getEntityJson(), capturedEntity.getEntityJson());
    }
}