package com.bank.account.handler;

import com.bank.account.exception.AccountDetailsNotFoundException;
import com.bank.account.exception.AuditNotFoundException;
import com.bank.account.util.AccountDetailsErrorResponse;
import com.bank.account.util.AuditErrorResponse;
import liquibase.pro.packaged.S;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;
import java.util.Map;

@RestControllerAdvice
public class AccountExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(AccountExceptionHandler.class);

    @ExceptionHandler({AccountDetailsNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public AccountDetailsErrorResponse handle(AccountDetailsNotFoundException ignoredE) {
        logger.info("запущен метод AccountDetailsErrorResponse");
        return new AccountDetailsErrorResponse("AccountDetails с таким id не найден", OffsetDateTime.now());
    }

    @ExceptionHandler({AuditNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public AuditErrorResponse handle(AuditNotFoundException ignoredE) {
        logger.info("запущен метод AuditErrorResponse");
        return new AuditErrorResponse("Audit с таким id не найден", OffsetDateTime.now());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(
            @NonNull MethodArgumentNotValidException e) {
        Map<String, String> errors = null;
        e.getAllErrors().forEach(objectError -> errors.put(objectError.getObjectName(),objectError.getDefaultMessage()));
        return ResponseEntity.badRequest().body(e.getBindingResult().getAllErrors());
    }
}