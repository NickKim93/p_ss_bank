package com.bank.antifraud.service;

import com.bank.antifraud.entity.AuditEntity;

import java.util.List;

public interface AuditService {
    List<AuditEntity> findAll();
}
