package com.bank.antifraud.util;

import com.bank.antifraud.entity.AuditEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class AuditingEntityListenerTest {

    private AuditingEntityListener listener;

    @BeforeEach
    public void setUp() {
        listener = new AuditingEntityListener();
    }

    @Test
    public void testPrePersist() {
        AuditEntity entity = new AuditEntity();
        listener.prePersist(entity);
        assertNotNull(entity.getEntityType());
        assertNotNull(entity.getOperationType());
        assertNotNull(entity.getModifiedAt());
        assertNotNull(entity.getNewEntityJson());
        assertNotNull(entity.getModifiedBy());
        assertNotNull(entity.getCreatedBy());
        assertNotNull(entity.getCreatedAt());
    }

    @Test
    public void testPreUpdate() {
        AuditEntity entity = new AuditEntity();
        listener.preUpdate(entity);
        assertNotNull(entity.getEntityType());
        assertNotNull(entity.getOperationType());
        assertNotNull(entity.getModifiedAt());
        assertNotNull(entity.getNewEntityJson());
        assertNotNull(entity.getModifiedBy());
    }
}