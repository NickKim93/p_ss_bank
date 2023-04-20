package com.bank.antifraud.service;

import com.bank.antifraud.entity.CardEntity;
import com.bank.antifraud.repository.CardRepository;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl extends SuspiciousTransferServiceImpl<CardEntity> {
    public CardServiceImpl(CardRepository cardRepository) {
        super(cardRepository, CardEntity.class);
    }
}
