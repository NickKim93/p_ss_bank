package com.bank.antifraud.controller;

import com.bank.antifraud.dto.SuspiciousTransferDto;
import com.bank.antifraud.entity.PhoneEntity;
import com.bank.antifraud.service.SuspiciousTransferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Контроллер, который работает с сущность {@link PhoneEntity}
 *
 * @author Makariy Petrov
 */
@RestController
@RequestMapping("phone/transfers")
@Tag(name = "Подозрительные переводы по номеру телефона")
public class SuspiciousPhoneTransfersController {
    private final SuspiciousTransferService<PhoneEntity> suspiciousPhoneTransfersService;

    public SuspiciousPhoneTransfersController(SuspiciousTransferService<PhoneEntity> suspiciousPhoneTransfersService) {
        this.suspiciousPhoneTransfersService = suspiciousPhoneTransfersService;
    }

    @GetMapping("all")
    @Operation(description = "Получить список всех переводов")
    public ResponseEntity<List<PhoneEntity>> getAll() {
        return ResponseEntity.ok(suspiciousPhoneTransfersService.findAll());
    }

    @GetMapping("{id}")
    @Operation(description = "Получить перевод по id")
    public ResponseEntity<PhoneEntity> getById(@PathVariable Long id) {
        return ResponseEntity.ok(suspiciousPhoneTransfersService.findById(id));
    }

    @DeleteMapping("{id}")
    @Operation(description = "Удалить перевод по id")
    public ResponseEntity<Long> deleteAudit(@PathVariable Long id) {
        suspiciousPhoneTransfersService.delete(id);
        return ResponseEntity.ok(id);
    }

    @PostMapping
    @Operation(description = "Сохранить новый перевод")
    public ResponseEntity<PhoneEntity> save(@Valid @RequestBody SuspiciousTransferDto suspiciousPhoneTransfersDto) {
        return ResponseEntity.ok(suspiciousPhoneTransfersService.save(suspiciousPhoneTransfersDto));
    }

    @PatchMapping
    @Operation(description = "Обновить существующий перевод")
    public ResponseEntity<PhoneEntity> update(@Valid @RequestBody SuspiciousTransferDto suspiciousPhoneTransfersDto) {
        return ResponseEntity.ok(suspiciousPhoneTransfersService.update(suspiciousPhoneTransfersDto));
    }
}
