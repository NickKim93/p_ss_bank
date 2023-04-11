package com.bank.antifraud.controller;

import com.bank.antifraud.dto.AuditDto;
import com.bank.antifraud.entity.AuditEntity;
import com.bank.antifraud.service.AuditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(AuditController.class);
    private final AuditService auditService;

    public AuditController(AuditService auditService) {
        this.auditService = auditService;
    }

    /**
     * Возвращает все сущности, которые есть в базе
     * @return возвращает все сушности
     */
    @GetMapping("all")
    public ResponseEntity<List<AuditDto>> getAll() {
        LOGGER.info("Контроллер получил запрос на выдачу всех сущностей audit");
        return ResponseEntity.ok(auditService.findAll());
    }

    /**
     * Ищет сущность по id, либо выбрасывает исключение EntityNotFoundException,
     * которое обрабатывается в DefaultHandler
     * @param id числовое значение id сущности
     * @return сущность с заданным id
     */
    @GetMapping("{id}")
    public ResponseEntity<AuditDto> getById(@PathVariable BigInteger id) {
        LOGGER.info("Контроллер получил запрос на audit с id: " + id.toString());
        return ResponseEntity.ok(auditService.findById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<BigInteger> deleteAudit(@PathVariable BigInteger id) {
        LOGGER.info("Контроллер получил запрос на удаление сущности audit с id: " + id.toString());
        auditService.delete(id);
        return ResponseEntity.ok(id);
    }

    @PostMapping
    public ResponseEntity<AuditDto> save(@RequestBody AuditDto auditDto) {
        LOGGER.info("Контроллер получил запрос на сохранение audit: " + auditDto.toString());
        return ResponseEntity.ok(auditService.save(auditDto));
    }

    @PatchMapping
    public ResponseEntity<AuditDto> update(@RequestBody AuditDto auditDto) {
        LOGGER.info("Контроллер получил запрос на обновление сущности audit с id: " + auditDto.toString());
        return ResponseEntity.ok(auditService.update(auditDto));
    }
}
