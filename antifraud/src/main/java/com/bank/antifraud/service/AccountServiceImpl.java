package com.bank.antifraud.service;

import com.bank.antifraud.entity.AccountEntity;
import com.bank.antifraud.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl extends SuspiciousTransferServiceImpl<AccountEntity> {
    public AccountServiceImpl(AccountRepository accountRepository) {
        super(accountRepository, AccountEntity.class);
    }
}
