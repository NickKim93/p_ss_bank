package com.bank.transfer.service;
import com.bank.transfer.entity.PhoneTransferEntity;
import java.util.List;
import java.util.Optional;

public interface PhoneTransferService {
    List<PhoneTransferEntity> getAll();
    Optional<PhoneTransferEntity> getById(Long id);
    void save(PhoneTransferEntity phoneTransferEntity);
    void update(Long id, PhoneTransferEntity phoneTransferEntity);
    void delete(Long id);
    Optional<PhoneTransferEntity> getByPhoneNumber(Long phoneNumber);
}
