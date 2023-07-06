package com.bank.antifraud.controller;

import com.bank.antifraud.dto.SuspiciousTransferDto;
import com.bank.antifraud.entity.CardEntity;
import com.bank.antifraud.service.SuspiciousTransferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Контроллер, который работает с сущность {@link CardEntity}
 *
 * @author Makariy Petrov
 */
@RestController
@RequestMapping("card/transfer")
@Tag(name = "Подозрительные переводы по номеру карты")
public class SuspiciousCardTransferController {
    private final SuspiciousTransferService<CardEntity> suspiciousCardTransferService;

    public SuspiciousCardTransferController(SuspiciousTransferService<CardEntity> suspiciousCardTransferService) {
        this.suspiciousCardTransferService = suspiciousCardTransferService;
    }

    @GetMapping("all")
    @Operation(description = "Получить список всех переводов")
    public ResponseEntity<List<CardEntity>> getAll() {
        return ResponseEntity.ok(suspiciousCardTransferService.findAll());
    }

    @GetMapping("{id}")
    @Operation(description = "Получить перевод по id")
    public ResponseEntity<CardEntity> getById(@PathVariable Long id) {
        return ResponseEntity.ok(suspiciousCardTransferService.findById(id));
    }

    @DeleteMapping("{id}")
    @Operation(description = "Удалить перевод по id")
    public ResponseEntity<Long> deleteAudit(@PathVariable Long id) {
        suspiciousCardTransferService.delete(id);
        return ResponseEntity.ok(id);
    }

    @PostMapping
    @Operation(description = "Сохранить новый перевод")
    public ResponseEntity<CardEntity> save(@Valid @RequestBody SuspiciousTransferDto suspiciousCardTransferDto) {
        return ResponseEntity.ok(suspiciousCardTransferService.save(suspiciousCardTransferDto));
    }

    @PatchMapping
    @Operation(description = "Обновить существующий перевод")
    public ResponseEntity<CardEntity> update(@Valid @RequestBody SuspiciousTransferDto suspiciousCardTransferDto) {
        return ResponseEntity.ok(suspiciousCardTransferService.update(suspiciousCardTransferDto));
    }
}
