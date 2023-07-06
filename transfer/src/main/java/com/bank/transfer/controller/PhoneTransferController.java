package com.bank.transfer.controller;


import com.bank.transfer.dto.PhoneTransferDto;
import com.bank.transfer.entity.AuditEntity;
import com.bank.transfer.exception.PhoneTransferEntityNotFoundException;
import com.bank.transfer.exception.PhoneTransferValidatorException;
import com.bank.transfer.mapper.PhoneTransferMapper;
import com.bank.transfer.service.AuditService;
import com.bank.transfer.service.PhoneTransferService;
import com.bank.transfer.utils.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/phone_transfers")
public class PhoneTransferController {

    private final PhoneTransferService phoneTransferService;
    private final AuditService auditService;

    public PhoneTransferController(PhoneTransferService phoneTransferService, AuditService auditService) {
        this.phoneTransferService = phoneTransferService;
        this.auditService = auditService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PhoneTransferDto>> getAll() {
        var dtoList = phoneTransferService.getAll()
                .stream()
                .map(PhoneTransferMapper.PHONE_TRANSFER_MAPPER::entityToDtoPhoneTransfer)
                .collect(Collectors.toList());
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PhoneTransferDto> getById(@PathVariable("id") Long id) {
        var phoneTransferEntity =
                phoneTransferService
                        .getById(id)
                        .orElseThrow(() -> new PhoneTransferEntityNotFoundException(String
                                .format("phoneTransferEntity with id = %d not found", id)));
        var dto = PhoneTransferMapper.PHONE_TRANSFER_MAPPER.entityToDtoPhoneTransfer(phoneTransferEntity);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PhoneTransferDto> create(
            @RequestBody @Valid PhoneTransferDto phoneTransferDto,
            BindingResult bindingResult) {
        var phoneTransferEntity = PhoneTransferMapper.PHONE_TRANSFER_MAPPER.dtoToEntityPhoneTransfer(phoneTransferDto);
        if (bindingResult.hasErrors()) {
            throw new PhoneTransferValidatorException(Utils.getMassageOfError(bindingResult));
        }
        phoneTransferService.save(phoneTransferEntity);
        AuditEntity auditEntity = new AuditEntity();
        auditEntity.setEntityType("PhoneTransferEntity");
        auditEntity.setOperationType("SAVE");
        auditEntity.setEntityJson(phoneTransferEntity.toString());
        auditEntity.setNewEntityJson(phoneTransferEntity.toString());
        auditService.save(auditEntity);
        var savedDto = PhoneTransferMapper.PHONE_TRANSFER_MAPPER.entityToDtoPhoneTransfer(phoneTransferEntity);
        return new ResponseEntity<>(savedDto, HttpStatus.CREATED);//201
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PhoneTransferDto> update(@PathVariable("id") Long id,
                                                   @RequestBody @Valid PhoneTransferDto phoneTransferDto,
                                                   BindingResult bindingResult) {
        var oldTransfer = phoneTransferService.getById(id)
                .orElseThrow(() -> new PhoneTransferEntityNotFoundException(String
                        .format("phoneTransferEntity with id = %d not found", id)));
        var phoneTransferEntity = PhoneTransferMapper.PHONE_TRANSFER_MAPPER.dtoToEntityPhoneTransfer(phoneTransferDto);
        if (bindingResult.hasErrors()) {
            throw new PhoneTransferValidatorException(Utils.getMassageOfError(bindingResult));
        }
        phoneTransferService.update(id, phoneTransferEntity);
        AuditEntity auditEntity = new AuditEntity();
        auditEntity.setEntityType("phoneTransferEntity");
        auditEntity.setOperationType("PUT_UPDATE");
        auditEntity.setNewEntityJson(phoneTransferEntity.toString());
        auditEntity.setEntityJson(oldTransfer.toString());
        auditService.save(auditEntity);
        var updatedDto = PhoneTransferMapper.PHONE_TRANSFER_MAPPER.entityToDtoPhoneTransfer(phoneTransferEntity);
        return new ResponseEntity<>(updatedDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        var transferFromDataBase =
                phoneTransferService.getById(id)
                        .orElseThrow(() -> new PhoneTransferEntityNotFoundException(String
                                .format("phoneTransferEntity with id = %d not found", id)));
        phoneTransferService.delete(id);
        AuditEntity auditEntity = new AuditEntity();
        auditEntity.setEntityType("PhoneTransferEntity");
        auditEntity.setOperationType("DELETE");
        auditEntity.setNewEntityJson(transferFromDataBase.toString());
        auditEntity.setEntityJson(transferFromDataBase.toString());
        auditService.save(auditEntity);
    }


}
