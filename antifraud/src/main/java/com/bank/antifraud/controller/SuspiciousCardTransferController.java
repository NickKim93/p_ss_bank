package com.bank.antifraud.controller;

import com.bank.antifraud.dto.SuspiciousCardTransferDto;
import com.bank.antifraud.entity.SuspiciousCardTransferEntity;
import com.bank.antifraud.service.SuspiciousCardTransferService;
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
    public ResponseEntity<SuspiciousCardTransferEntity> getById(@PathVariable BigInteger id) {
        return ResponseEntity.ok(suspiciousCardTransferService.findById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<BigInteger> deleteAudit(@PathVariable BigInteger id) {
        suspiciousCardTransferService.delete(id);
        return ResponseEntity.ok(id);
    }

    @PostMapping
    public ResponseEntity<SuspiciousCardTransferEntity> save(@RequestBody SuspiciousCardTransferDto suspiciousCardTransferDto) {
        return ResponseEntity.ok(suspiciousCardTransferService.save(suspiciousCardTransferDto));
    }

    @PatchMapping
    public ResponseEntity<SuspiciousCardTransferEntity> update(@RequestBody SuspiciousCardTransferDto suspiciousCardTransferDto) {
        return ResponseEntity.ok(suspiciousCardTransferService.update(suspiciousCardTransferDto));
    }
}
