package com.bank.publicinfo.controller;

import com.bank.publicinfo.dto.AuditDto;
import com.bank.publicinfo.service.AuditService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/audits")
public class AuditController {
    private final AuditService auditService;


    public AuditController(AuditService auditService) {
        this.auditService = auditService;
    }

    @GetMapping
    public ResponseEntity<List<AuditDto>> getAllAudits() {
        List<AuditDto> auditDtoList = auditService.getAudits();
        return ResponseEntity.ok(auditDtoList);
    }

    @PostMapping()
    public ResponseEntity<AuditDto> createAudit (@RequestBody AuditDto auditDto) {
        AuditDto createdAudit = auditService.createAudit(auditDto);
        return ResponseEntity
                .created(URI.create("/audits" + createdAudit.id()))
                .body(createdAudit);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuditDto> update(@PathVariable BigInteger id, @RequestBody AuditDto auditDto) {
        AuditDto updatedAudit = auditService.updateAudit(id, auditDto);
        return ResponseEntity.ok(updatedAudit);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable BigInteger id) {
        auditService.deleteAuditById(id);
        return ResponseEntity.noContent().build();
    }
}
