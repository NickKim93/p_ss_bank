package com.bank.account.controller;

import com.bank.account.dto.AccountDetailsDTO;
import com.bank.account.service.AccountDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/details")
@RequiredArgsConstructor
@Tag(name = "AccountDetailsController", description = "Этот микросервис отвечает за банковский счета и за все операции, " +
        "которые отвечают за логику банковского счета и за изменение сущностей данного микросервиса")
public class AccountDetailsController {
    private final AccountDetailsService accountService;
    private final Logger logger = LoggerFactory.getLogger(AccountDetailsController.class);

    @PostMapping
    @Operation(summary  = "Создание новой записи")
    ResponseEntity<HttpStatus> saveAccountDetails(@RequestBody @Valid AccountDetailsDTO detailsDTO) {
        logger.info("запущен метод saveAccountDetails");
        accountService.save(detailsDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary  = "Получение записи по id")
    ResponseEntity<AccountDetailsDTO> getAccountDetails(@PathVariable Long id) {
        logger.info("запущен метод getAccountDetails");
        return new ResponseEntity<>(accountService.getAccountDetails(id), HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary  = "Получение всех записей")
    ResponseEntity<List<AccountDetailsDTO>> getAllAccountDetails() {
        logger.info("запущен метод getAllAccountDetails");
        return new ResponseEntity<>(accountService.getAllAccountDetails(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary  = "Удаление записи")
    ResponseEntity<HttpStatus> deleteAccountDetails(@PathVariable Long id) {
        logger.info("запущен метод deleteAccountDetails");
        accountService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping
    @Operation(summary  = "Обновление существующей записи")
    ResponseEntity<HttpStatus> updateAccountDetails(@RequestBody @Valid AccountDetailsDTO detailsDTO) {
        logger.info("запущен метод updateAccountDetails");
        accountService.updateAccountDetails(detailsDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}