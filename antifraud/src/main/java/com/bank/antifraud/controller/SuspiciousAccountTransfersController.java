package com.bank.antifraud.controller;

import com.bank.antifraud.dto.SuspiciousTransferDto;
import com.bank.antifraud.entity.AccountEntity;
import com.bank.antifraud.service.SuspiciousTransferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Контроллер, который работает с сущность {@link AccountEntity}
 *
 * @author Makariy Petrov
 */
@RestController
@RequestMapping("account/transfers")
@Tag(name = "Подозрительные переводы по номеру счета")
public class SuspiciousAccountTransfersController {
    private final SuspiciousTransferService<AccountEntity> suspiciousAccountTransfersService;

    public SuspiciousAccountTransfersController(SuspiciousTransferService<AccountEntity> suspiciousAccountTransfersService) {
        this.suspiciousAccountTransfersService = suspiciousAccountTransfersService;
    }

    @GetMapping("all")
    @Operation(description = "Получить список всех переводов")
    public ResponseEntity<List<AccountEntity>> getAll() {
        return ResponseEntity.ok(suspiciousAccountTransfersService.findAll());
    }

    @GetMapping("{id}")
    @Operation(description = "Получить перевод по id")
    public ResponseEntity<AccountEntity> getById(@PathVariable Long id) {
        return ResponseEntity.ok(suspiciousAccountTransfersService.findById(id));
    }

    @DeleteMapping("{id}")
    @Operation(description = "Удалить перевод по id")
    public ResponseEntity<Long> deleteAudit(@PathVariable Long id) {
        suspiciousAccountTransfersService.delete(id);
        return ResponseEntity.ok(id);
    }

    @PostMapping
    @Operation(description = "Сохранить новый перевод")
    public ResponseEntity<AccountEntity> save(@Valid @RequestBody SuspiciousTransferDto suspiciousAccountTransfersDto) {
        return ResponseEntity.ok(suspiciousAccountTransfersService.save(suspiciousAccountTransfersDto));
    }

    @PatchMapping
    @Operation(description = "Обновить существующий перевод")
    public ResponseEntity<AccountEntity> update(@Valid @RequestBody SuspiciousTransferDto suspiciousAccountTransfersDto) {
        return ResponseEntity.ok(suspiciousAccountTransfersService.update(suspiciousAccountTransfersDto));
    }
}
