package com.bank.antifraud.service;

import com.bank.antifraud.entity.PhoneEntity;
import com.bank.antifraud.repository.PhoneRepository;
import io.micrometer.core.annotation.Timed;
import org.springframework.stereotype.Service;

@Service
public class PhoneServiceImpl extends SuspiciousTransferServiceImpl<PhoneEntity> {
    public PhoneServiceImpl(PhoneRepository phoneRepository) {
        super(phoneRepository, PhoneEntity.class);
    }
}
