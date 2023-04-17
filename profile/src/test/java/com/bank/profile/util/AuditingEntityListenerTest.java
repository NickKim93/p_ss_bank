package com.bank.profile.util;

import com.bank.profile.entity.Audit;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class AuditingEntityListenerTest {

    Audit audit;

    @Mock
    ObjectMapper objectMapper;

    @InjectMocks
    AuditingEntityListener auditingEntityListener;

    @BeforeEach
    void setUp() {
        audit = new Audit();
        audit.setEntityType("profile");
        audit.setOperationType(OperationType.CREATE.name());
        audit.setCreatedBy("SYSTEM");
        audit.setModifiedBy("SYSTEM");
        audit.setCreatedAt(OffsetDateTime.now());
        audit.setModifiedAt(OffsetDateTime.now());
        audit.setNewEntityJson("");
        audit.setEntityJson("");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void prePersist() {
    }

    @Test
    void preUpdate() {
    }

    @Test
    @DisplayName("Неудачная попытка преобразования объекта Audit в JSON ObjectMapper`ом, JsonProcessingException")
    void handleCreateStructure_ObjectAuditIsNotValid_JsonProcessingException() throws JsonProcessingException {
//        // given
//        doThrow(JsonProcessingException.class).when(objectMapper).writeValueAsString(audit);
//
//        // when
//
//        // then
//        assertThrows(JsonProcessingException.class, () -> {
//            auditingEntityListener.createStructure(audit, OperationType.CREATE);
//        });
    }
}