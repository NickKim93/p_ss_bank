package com.bank.antifraud.controller;

import com.bank.antifraud.dto.SuspiciousAccountTransfersDto;
import com.bank.antifraud.entity.SuspiciousAccountTransfersEntity;
import com.bank.antifraud.service.SuspiciousAccountTransfersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Контроллер, который работает с сущность {@link SuspiciousAccountTransfersEntity}
 *
 * @author Makariy Petrov
 */
@RestController
@RequestMapping("account/transfers")
public class SuspiciousAccountTransfersController {
    private final SuspiciousAccountTransfersService suspiciousAccountTransfersService;

    public SuspiciousAccountTransfersController(SuspiciousAccountTransfersService suspiciousAccountTransfersService) {
        this.suspiciousAccountTransfersService = suspiciousAccountTransfersService;
    }

    @GetMapping("all")
    public ResponseEntity<List<SuspiciousAccountTransfersEntity>> getAll() {
        return ResponseEntity.ok(suspiciousAccountTransfersService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<SuspiciousAccountTransfersEntity> getById(@PathVariable Long id) {
        return ResponseEntity.ok(suspiciousAccountTransfersService.findById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Long> deleteAudit(@PathVariable Long id) {
        suspiciousAccountTransfersService.delete(id);
        return ResponseEntity.ok(id);
    }

    @PostMapping
    public ResponseEntity<SuspiciousAccountTransfersEntity> save(@Valid @RequestBody SuspiciousAccountTransfersDto suspiciousAccountTransfersDto) {
        return ResponseEntity.ok(suspiciousAccountTransfersService.save(suspiciousAccountTransfersDto));
    }

    @PatchMapping
    public ResponseEntity<SuspiciousAccountTransfersEntity> update(@Valid @RequestBody SuspiciousAccountTransfersDto suspiciousAccountTransfersDto) {
        return ResponseEntity.ok(suspiciousAccountTransfersService.update(suspiciousAccountTransfersDto));
    }
}
