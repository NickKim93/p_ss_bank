package com.bank.antifraud.service;

import com.bank.antifraud.dto.SuspiciousTransferDto;
import com.bank.antifraud.entity.SuspiciousTransfer;

import java.util.List;

/**
 * Бизнес логика для работы с подозрительными переводами
 *
 * @param <T> работает с наследниками класса SuspiciousTransfer, то есть:
 *            {@link com.bank.antifraud.entity.PhoneEntity}, {@link com.bank.antifraud.entity.CardEntity}, {@link com.bank.antifraud.entity.AccountEntity}
 * @author Makariy Petrov
 */
public interface SuspiciousTransferService<T extends SuspiciousTransfer> {
    T save(SuspiciousTransferDto suspiciousTransferDto);

    T findById(Long id);

    List<T> findAll();

    void delete(Long id);

    T update(SuspiciousTransferDto suspiciousTransferDto);
}
