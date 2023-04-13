package com.bank.antifraud.controller;

import com.bank.antifraud.dto.SuspiciousCardTransferDto;
import com.bank.antifraud.entity.SuspiciousCardTransferEntity;
import com.bank.antifraud.service.SuspiciousCardTransferService;
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

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("card/transfer")
public class SuspiciousCardTransferController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SuspiciousCardTransferController.class);
    private final SuspiciousCardTransferService suspiciousCardTransferService;

    public SuspiciousCardTransferController(SuspiciousCardTransferService suspiciousCardTransferService) {
        this.suspiciousCardTransferService = suspiciousCardTransferService;
    }

    @GetMapping("all")
    public ResponseEntity<List<SuspiciousCardTransferEntity>> getAll() {
        LOGGER.info("В SuspiciousCardTransferController сработал метод getAll");

        return ResponseEntity.ok(suspiciousCardTransferService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<SuspiciousCardTransferEntity> getById(@PathVariable Long id) {
        LOGGER.info("В SuspiciousCardTransferController сработал метод getById, с id: " + id.toString());

        return ResponseEntity.ok(suspiciousCardTransferService.findById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Long> deleteAudit(@PathVariable Long id) {
        LOGGER.info("В SuspiciousCardTransferController сработал метод deleteAudit, с id: " + id.toString());

        suspiciousCardTransferService.delete(id);
        return ResponseEntity.ok(id);
    }

    @PostMapping
    public ResponseEntity<SuspiciousCardTransferEntity> save(@Valid @RequestBody SuspiciousCardTransferDto suspiciousCardTransferDto) {
        LOGGER.info("В SuspiciousCardTransferController сработал метод save: \n" + suspiciousCardTransferDto.toString());

        return ResponseEntity.ok(suspiciousCardTransferService.save(suspiciousCardTransferDto));
    }

    @PatchMapping
    public ResponseEntity<SuspiciousCardTransferEntity> update(@Valid @RequestBody SuspiciousCardTransferDto suspiciousCardTransferDto) {
        LOGGER.info("В SuspiciousCardTransferController сработал метод update: \n" + suspiciousCardTransferDto.toString());

        return ResponseEntity.ok(suspiciousCardTransferService.update(suspiciousCardTransferDto));
    }
}
