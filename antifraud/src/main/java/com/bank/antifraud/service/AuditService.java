package com.bank.antifraud.service;

import com.bank.antifraud.entity.AuditEntity;

import java.util.List;

/**
 * Бизнес-логика для работы с сущносью аудит
 *
 * @author Makariy Petrov
 */
public interface AuditService {
    List<AuditEntity> findAll();
}
