package com.bank.transfer.handler;

import com.bank.transfer.exception.CardTransferEntityNotFoundException;
import com.bank.transfer.exception.CardTransferValidatorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class CardTransferEntityControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CardTransferEntityNotFoundException.class)
    public ResponseEntity<String> handleCardTransferNotFoundException(CardTransferEntityNotFoundException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CardTransferValidatorException.class)
    public ResponseEntity<String> handleCardTransferException(CardTransferValidatorException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
