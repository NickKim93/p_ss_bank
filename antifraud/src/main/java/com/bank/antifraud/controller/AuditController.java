package com.bank.antifraud.controller;

import com.bank.antifraud.entity.AuditEntity;
import com.bank.antifraud.repository.AuditRepository;
import com.bank.antifraud.service.AuditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("audit")
public class AuditController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SuspiciousAccountTransfersController.class);
    private final AuditService auditService;

    public AuditController(AuditService auditService) {
        this.auditService = auditService;
    }

    @GetMapping("all")
    public ResponseEntity<List<AuditEntity>> findAll() {
        return ResponseEntity.ok(auditService.findAll());
    }
}