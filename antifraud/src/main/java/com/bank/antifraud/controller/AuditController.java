package com.bank.antifraud.controller;

import com.bank.antifraud.entity.AuditEntity;
import com.bank.antifraud.service.AuditService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Контроллер, который работает с сущность {@link AuditEntity}
 *
 * @author Makariy Petrov
 */
@RestController
@RequestMapping("audit")
public class AuditController {
    private final AuditService auditService;

    public AuditController(AuditService auditService) {
        this.auditService = auditService;
    }

    @GetMapping("all")
    public ResponseEntity<List<AuditEntity>> findAll() {
        return ResponseEntity.ok(auditService.findAll());
    }
}