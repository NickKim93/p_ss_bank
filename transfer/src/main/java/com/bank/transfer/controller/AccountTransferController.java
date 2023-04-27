package com.bank.transfer.controller;

import com.bank.transfer.dto.AccountTransferDto;
import com.bank.transfer.entity.AuditEntity;
import com.bank.transfer.exception.AccountTransferEntityNotFoundException;
import com.bank.transfer.exception.AccountTransferValidatorException;
import com.bank.transfer.mapper.AccountTransferMapper;
import com.bank.transfer.service.AccountTransferService;
import com.bank.transfer.service.AuditService;
import com.bank.transfer.utils.Utils;
import com.bank.transfer.validator.AccountTransferEntityAccountNumberUniqueValidator;
import io.micrometer.core.annotation.Timed;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/account_transfers")
public class AccountTransferController {

    private final AccountTransferService accountTransferService;
    private final AccountTransferEntityAccountNumberUniqueValidator accountTransferValidator;
    private final AuditService auditService;

    public AccountTransferController(AccountTransferService accountTransferService, AccountTransferEntityAccountNumberUniqueValidator accountTransferValidator, AuditService auditService) {
        this.accountTransferService = accountTransferService;
        this.accountTransferValidator = accountTransferValidator;
        this.auditService = auditService;
    }

    @Timed("GetAllFromController")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AccountTransferDto>> getAll() {
        var dtoList = accountTransferService.getAll()
                .stream()
                .map(AccountTransferMapper.ACCOUNT_TRANSFER_MAPPER::entityToDtoAccountTransfer)
                .collect(Collectors.toList());
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountTransferDto> getById(@PathVariable("id") Long id) {
        var accountTransferEntity =
                accountTransferService
                        .getById(id)
                        .orElseThrow(() -> new AccountTransferEntityNotFoundException(String
                                .format("accountTransferEntity with id = %d not found", id)));
        var dto = AccountTransferMapper.ACCOUNT_TRANSFER_MAPPER.entityToDtoAccountTransfer(accountTransferEntity);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountTransferDto> create(@RequestBody @Valid AccountTransferDto dto,
                                                     BindingResult bindingResult) {
        var accountTransferEntity = AccountTransferMapper.ACCOUNT_TRANSFER_MAPPER.dtoToEntityAccountTransfer(dto);
        accountTransferValidator.validate(accountTransferEntity, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new AccountTransferValidatorException(Utils.getMassageOfError(bindingResult));
        }
        accountTransferService.save(accountTransferEntity);
        AuditEntity auditEntity = new AuditEntity();
        auditEntity.setEntityType("AccountTransferEntity");
        auditEntity.setOperationType("SAVE");
        auditEntity.setEntityJson(accountTransferEntity.toString());
        auditEntity.setNewEntityJson(accountTransferEntity.toString());
        auditService.save(auditEntity);
        var savedDto = AccountTransferMapper.ACCOUNT_TRANSFER_MAPPER.entityToDtoAccountTransfer(accountTransferEntity);
        return new ResponseEntity<>(savedDto, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountTransferDto> update(@PathVariable("id") Long id,
                                                     @RequestBody @Valid AccountTransferDto dto,
                                                     BindingResult bindingResult) {
        var oldTransfer = accountTransferService.getById(id)
                .orElseThrow(() -> new AccountTransferEntityNotFoundException(String
                        .format("accountTransferEntity with id = %d not found", id)));
        var accountTransferEntity = AccountTransferMapper.ACCOUNT_TRANSFER_MAPPER.dtoToEntityAccountTransfer(dto);
        accountTransferEntity.setId(id);
        accountTransferValidator.validate(accountTransferEntity, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new AccountTransferValidatorException(Utils.getMassageOfError(bindingResult));
        }
        accountTransferService.update(id, accountTransferEntity);
        AuditEntity auditEntity = new AuditEntity();
        auditEntity.setEntityType("AccountTransferEntity");
        auditEntity.setOperationType("PUT_UPDATE");
        auditEntity.setNewEntityJson(accountTransferEntity.toString());
        auditEntity.setEntityJson(oldTransfer.toString());
        auditService.save(auditEntity);
        var updatedDto = AccountTransferMapper.ACCOUNT_TRANSFER_MAPPER.entityToDtoAccountTransfer(accountTransferEntity);
        return new ResponseEntity<>(updatedDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        var accountTransferFromDatabase =
                accountTransferService.getById(id)
                        .orElseThrow(() -> new AccountTransferEntityNotFoundException(String
                                .format("accountTransferEntity with id= %d not found", id)));
        accountTransferService.delete(id);
        AuditEntity auditEntity = new AuditEntity();
        auditEntity.setEntityType("AccountTransferEntity");
        auditEntity.setOperationType("DELETE");
        auditEntity.setNewEntityJson(accountTransferFromDatabase.toString());
        auditEntity.setEntityJson(accountTransferFromDatabase.toString());
        auditService.save(auditEntity);
    }

}
