package com.bank.transfer.controller;

import com.bank.transfer.dto.AccountTransferDto;
import com.bank.transfer.dto.AuditDto;
import com.bank.transfer.dto.CardTransferDto;
import com.bank.transfer.dto.PhoneTransferDto;
import com.bank.transfer.entity.AccountTransferEntity;
import com.bank.transfer.entity.AuditEntity;
import com.bank.transfer.entity.CardTransferEntity;
import com.bank.transfer.entity.PhoneTransferEntity;
import com.bank.transfer.service.AccountTransferService;
import com.bank.transfer.service.AuditService;
import com.bank.transfer.service.CardTransferService;
import com.bank.transfer.service.PhoneTransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TransferRESTController {
    private final AccountTransferService accountTransferService;
    private final AuditService auditService;
    private final CardTransferService cardTransferService;
    private final PhoneTransferService phoneTransferService;

    public TransferRESTController(AccountTransferService accountTransferService, AuditService auditService,
                                  CardTransferService cardTransferService, PhoneTransferService phoneTransferService) {
        this.accountTransferService = accountTransferService;
        this.auditService = auditService;
        this.cardTransferService = cardTransferService;
        this.phoneTransferService = phoneTransferService;
    }

    @GetMapping("/audit")
    public ResponseEntity<List<AuditEntity>> getAllAuditEntities() {
        return ResponseEntity.ok(auditService.getAll());
    }

    @GetMapping("/account")
    public ResponseEntity<List<AccountTransferEntity>> getAllAccountTransferEntities() {
        return ResponseEntity.ok(accountTransferService.getAll());
    }

    @GetMapping("/card")
    public ResponseEntity<List<CardTransferEntity>> getAllCardTransferEntities() {
        return ResponseEntity.ok(cardTransferService.getAll());
    }

    @GetMapping("/phone")
    public ResponseEntity<List<PhoneTransferEntity>> getAllPhoneTransferEntities() {
        return ResponseEntity.ok(phoneTransferService.getAll());
    }

    @GetMapping("/audit/{id}")
    public ResponseEntity<AuditEntity> getAuditEntityById(@PathVariable BigInteger id) {
        return ResponseEntity.ok(auditService.getById(id));
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<AccountTransferEntity> getAccountTransferEntityById(@PathVariable BigInteger id) {
        return ResponseEntity.ok(accountTransferService.getById(id));
    }

    @GetMapping("/card/{id}")
    public ResponseEntity<CardTransferEntity> getCardTransferEntityById(@PathVariable BigInteger id) {
        return ResponseEntity.ok(cardTransferService.getById(id));
    }

    @GetMapping("/phone/{id}")
    public ResponseEntity<PhoneTransferEntity> getPhoneTransferEntityById(@PathVariable BigInteger id) {
        return ResponseEntity.ok(phoneTransferService.getById(id));
    }

    @DeleteMapping("/audit/{id}")
    public ResponseEntity<BigInteger> deleteAudit(@PathVariable BigInteger id) {
        auditService.delete(id);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/account/{id}")
    public ResponseEntity<BigInteger> deleteAccount(@PathVariable BigInteger id) {
        accountTransferService.delete(id);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/card/{id}")
    public ResponseEntity<BigInteger> deleteCard(@PathVariable BigInteger id) {
        cardTransferService.delete(id);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/phone/{id}")
    public ResponseEntity<BigInteger> deletePhone(@PathVariable BigInteger id) {
        phoneTransferService.delete(id);
        return ResponseEntity.ok(id);
    }

    @PostMapping("/audit")
    public ResponseEntity<AuditEntity> saveAuditEntity(@RequestBody AuditDto auditDto) {
        return ResponseEntity.ok(auditService.saveOrUpdate(auditDto));
    }

    @PostMapping("/account")
    public ResponseEntity<AccountTransferEntity> saveAccountTransferEntity(
            @RequestBody AccountTransferDto accountTransferDto) {
        return ResponseEntity.ok(accountTransferService.saveOrUpdate(accountTransferDto));
    }

    @PostMapping("/card")
    public ResponseEntity<CardTransferEntity> saveCardTransferEntity(
            @RequestBody CardTransferDto cardTransferDto) {
        return ResponseEntity.ok(cardTransferService.saveOrUpdate(cardTransferDto));
    }

    @PostMapping("/phone")
    public ResponseEntity<PhoneTransferEntity> savePhoneTransferEntity(
            @RequestBody PhoneTransferDto phoneTransferDto) {
        return ResponseEntity.ok(phoneTransferService.saveOrUpdate(phoneTransferDto));
    }

    @PutMapping("/audit")
    public ResponseEntity<AuditEntity> updateAuditEntity(@RequestBody AuditDto auditDto) {
        return ResponseEntity.ok(auditService.saveOrUpdate(auditDto));
    }

    @PutMapping("/account")
    public ResponseEntity<AccountTransferEntity> updateAccountTransferEntity(
            @RequestBody AccountTransferDto accountTransferDto) {
        return ResponseEntity.ok(accountTransferService.saveOrUpdate(accountTransferDto));
    }

    @PutMapping("/card")
    public ResponseEntity<CardTransferEntity> updateCardTransferEntity(
            @RequestBody CardTransferDto cardTransferDto) {
        return ResponseEntity.ok(cardTransferService.saveOrUpdate(cardTransferDto));
    }

    @PutMapping("/phone")
    public ResponseEntity<PhoneTransferEntity> updatePhoneTransferEntity(
            @RequestBody PhoneTransferDto phoneTransferDto) {
        return ResponseEntity.ok(phoneTransferService.saveOrUpdate(phoneTransferDto));
    }
}
