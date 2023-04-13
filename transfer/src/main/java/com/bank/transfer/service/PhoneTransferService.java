package com.bank.transfer.service;
import com.bank.transfer.dto.PhoneTransferDto;
import com.bank.transfer.entity.PhoneTransferEntity;

import java.math.BigInteger;
import java.util.List;

public interface PhoneTransferService {
    List<PhoneTransferEntity> getAll();
    PhoneTransferEntity getById(BigInteger id);
    PhoneTransferEntity saveOrUpdate(PhoneTransferDto phoneTransferDto);
    void delete(BigInteger id);
}
