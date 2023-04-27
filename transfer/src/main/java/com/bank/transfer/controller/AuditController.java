package com.bank.transfer.controller;

import com.bank.transfer.dto.AuditDto;
import com.bank.transfer.exception.AuditEntityNotFoundException;
import com.bank.transfer.mapper.AuditMapper;
import com.bank.transfer.service.AuditService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/audits")
public class AuditController {

    private final AuditService auditService;

    public AuditController(AuditService auditService) {
        this.auditService = auditService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AuditDto>> getAll() {
        var dtoList = auditService.getAll()
                .stream()
                .map(AuditMapper.AUDIT_MAPPER::entityToDtoAudit)
                .collect(Collectors.toList());
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuditDto> getById(@PathVariable("id") Long id) {
        var auditEntity =
                auditService
                        .getById(id)
                        .orElseThrow(() -> new AuditEntityNotFoundException(String
                                .format("audit with id = %d not found", id)));
        var dtoAudit = AuditMapper.AUDIT_MAPPER.entityToDtoAudit(auditEntity);
        return new ResponseEntity<>(dtoAudit, HttpStatus.OK);
    }
}
