package com.bank.antifraud.controller;

import com.bank.antifraud.dto.SuspiciousCardTransferDto;
import com.bank.antifraud.entity.SuspiciousAccountTransfersEntity;
import com.bank.antifraud.entity.SuspiciousCardTransferEntity;
import com.bank.antifraud.service.SuspiciousCardTransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Контроллер, который работает с сущность {@link SuspiciousCardTransferEntity}
 *
 * @author Makariy Petrov
 */
@RestController
@RequestMapping("card/transfer")
public class SuspiciousCardTransferController {
    private final SuspiciousCardTransferService suspiciousCardTransferService;

    public SuspiciousCardTransferController(SuspiciousCardTransferService suspiciousCardTransferService) {
        this.suspiciousCardTransferService = suspiciousCardTransferService;
    }

    @GetMapping("all")
    public ResponseEntity<List<SuspiciousCardTransferEntity>> getAll() {
        return ResponseEntity.ok(suspiciousCardTransferService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<SuspiciousCardTransferEntity> getById(@PathVariable Long id) {
        return ResponseEntity.ok(suspiciousCardTransferService.findById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Long> deleteAudit(@PathVariable Long id) {
        suspiciousCardTransferService.delete(id);
        return ResponseEntity.ok(id);
    }

    @PostMapping
    public ResponseEntity<SuspiciousCardTransferEntity> save(@Valid @RequestBody SuspiciousCardTransferDto suspiciousCardTransferDto) {
        return ResponseEntity.ok(suspiciousCardTransferService.save(suspiciousCardTransferDto));
    }

    @PatchMapping
    public ResponseEntity<SuspiciousCardTransferEntity> update(@Valid @RequestBody SuspiciousCardTransferDto suspiciousCardTransferDto) {
        return ResponseEntity.ok(suspiciousCardTransferService.update(suspiciousCardTransferDto));
    }
}
