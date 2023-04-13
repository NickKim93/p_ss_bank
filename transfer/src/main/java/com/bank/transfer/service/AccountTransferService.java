package com.bank.transfer.service;

import com.bank.transfer.dto.AccountTransferDto;
import com.bank.transfer.entity.AccountTransferEntity;

import java.math.BigInteger;
import java.util.List;

public interface AccountTransferService {
    List<AccountTransferEntity> getAll();
    AccountTransferEntity getById(BigInteger id);
    AccountTransferEntity saveOrUpdate(AccountTransferDto accountTransferDto);
    void delete(BigInteger id);

}
