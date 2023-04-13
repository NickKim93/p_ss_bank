package com.bank.antifraud.controller;

import com.bank.antifraud.dto.SuspiciousPhoneTransfersDto;
import com.bank.antifraud.entity.SuspiciousPhoneTransfersEntity;
import com.bank.antifraud.service.SuspiciousPhoneTransfersService;
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
@RequestMapping("phone/transfers")
public class SuspiciousPhoneTransfersController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SuspiciousPhoneTransfersController.class);
    private final SuspiciousPhoneTransfersService suspiciousPhoneTransfersService;

    public SuspiciousPhoneTransfersController(SuspiciousPhoneTransfersService suspiciousPhoneTransfersService) {
        this.suspiciousPhoneTransfersService = suspiciousPhoneTransfersService;
    }

    @GetMapping("all")
    public ResponseEntity<List<SuspiciousPhoneTransfersEntity>> getAll() {
        LOGGER.info("В SuspiciousPhoneTransfersController сработал метод getAll");

        return ResponseEntity.ok(suspiciousPhoneTransfersService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<SuspiciousPhoneTransfersEntity> getById(@PathVariable Long id) {
        LOGGER.info("В SuspiciousPhoneTransfersController сработал метод getById, передан id: " + id.toString());

        return ResponseEntity.ok(suspiciousPhoneTransfersService.findById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Long> deleteAudit(@PathVariable Long id) {
        LOGGER.info("В SuspiciousPhoneTransfersController сработал метод deleteAudit, передан id: " + id.toString());

        suspiciousPhoneTransfersService.delete(id);
        return ResponseEntity.ok(id);
    }

    @PostMapping
    public ResponseEntity<SuspiciousPhoneTransfersEntity> save(@Valid @RequestBody SuspiciousPhoneTransfersDto suspiciousPhoneTransfersDto) {
        LOGGER.info("В SuspiciousPhoneTransfersController сработал метод save: \n" + suspiciousPhoneTransfersDto.toString());

        return ResponseEntity.ok(suspiciousPhoneTransfersService.save(suspiciousPhoneTransfersDto));
    }

    @PatchMapping
    public ResponseEntity<SuspiciousPhoneTransfersEntity> update(@Valid @RequestBody SuspiciousPhoneTransfersDto suspiciousPhoneTransfersDto) {
        LOGGER.info("В SuspiciousPhoneTransfersController сработал метод update: \n" + suspiciousPhoneTransfersDto.toString());

        return ResponseEntity.ok(suspiciousPhoneTransfersService.update(suspiciousPhoneTransfersDto));
    }
}
