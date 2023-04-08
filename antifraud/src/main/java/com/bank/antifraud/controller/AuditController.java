package com.bank.antifraud.controller;

import com.bank.antifraud.dto.AuditDto;
import com.bank.antifraud.entity.AuditEntity;
import com.bank.antifraud.service.AuditService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("audit")
public class AuditController {
    private final AuditService auditService;

    public AuditController(AuditService auditService) {
        this.auditService = auditService;
    }

    @GetMapping("all")
    public ResponseEntity<List<AuditEntity>> getAll() {
        return ResponseEntity.ok(auditService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<AuditEntity> getById(@PathVariable Long id) {
        return ResponseEntity.ok(auditService.findById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Long> deleteAudit(@PathVariable Long id) {
        auditService.delete(id);
        return ResponseEntity.ok(id);
    }

    @PostMapping
    public ResponseEntity<AuditEntity> save(@RequestBody AuditDto auditDto) {
        return ResponseEntity.ok(auditService.save(auditDto));
    }

    @PatchMapping
    public ResponseEntity<AuditEntity> update(@RequestBody AuditDto auditDto) {
        return ResponseEntity.ok(auditService.update(auditDto));
    }
}
