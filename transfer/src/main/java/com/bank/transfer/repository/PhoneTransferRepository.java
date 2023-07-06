package com.bank.transfer.repository;

import com.bank.transfer.entity.PhoneTransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhoneTransferRepository extends JpaRepository<PhoneTransferEntity, Long> {

    Optional<PhoneTransferEntity> getByPhoneNumber(Long phoneNumber);
}
