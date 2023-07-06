package com.bank.transfer.handler;

import com.bank.transfer.exception.AccountTransferEntityNotFoundException;
import com.bank.transfer.exception.AccountTransferValidatorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class AccountTransferEntityControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AccountTransferEntityNotFoundException.class)
    public ResponseEntity<String> handleAccountTransferNotFoundException(AccountTransferEntityNotFoundException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccountTransferValidatorException.class)
    public ResponseEntity<String> handleAccountTransferException(AccountTransferValidatorException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
