package com.bank.antifraud.util;

import com.bank.antifraud.repository.AuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuditStaticService {
    @Autowired
    public static AuditRepository auditRepository;
}
