package com.bank.transfer.service;

import com.bank.transfer.dto.CardTransferDto;
import com.bank.transfer.entity.CardTransferEntity;

import java.math.BigInteger;
import java.util.List;

public interface CardTransferService {
    List<CardTransferEntity> getAll();
    CardTransferEntity getById(BigInteger id);
    CardTransferEntity saveOrUpdate(CardTransferDto cardTransferDto);
    void delete(BigInteger id);
}
