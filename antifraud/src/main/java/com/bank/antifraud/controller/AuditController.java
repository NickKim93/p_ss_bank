package com.bank.antifraud.controller;

import com.bank.antifraud.dto.AuditDto;
import com.bank.antifraud.entity.AuditEntity;
import com.bank.antifraud.service.AuditService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

/**
 * Контроллер, позволяющий выполнять CRUD операции с сущностью audit
 * @author Makariy Petrov
 */
@RestController
@RequestMapping("audit")
public class AuditController {
    private final AuditService auditService;

    public AuditController(AuditService auditService) {
        this.auditService = auditService;
    }

    /**
     * Возвращает все сущности, которые есть в базе
     * @return возвращает все сушности
     */
    @GetMapping("all")
    public ResponseEntity<List<AuditEntity>> getAll() {
        return ResponseEntity.ok(auditService.findAll());
    }

    /**
     * Ищет сущность по id, либо выбрасывает исключение EntityNotFoundException,
     * которое обрабатывается в DefaultHandler
     * @param id числовое значение id сущности
     * @return сущность с заданным id
     */
    @GetMapping("{id}")
    public ResponseEntity<AuditEntity> getById(@PathVariable BigInteger id) {
        return ResponseEntity.ok(auditService.findById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<BigInteger> deleteAudit(@PathVariable BigInteger id) {
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
