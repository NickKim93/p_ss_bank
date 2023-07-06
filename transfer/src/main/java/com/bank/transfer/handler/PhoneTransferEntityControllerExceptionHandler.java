package com.bank.transfer.handler;

import com.bank.transfer.exception.PhoneTransferEntityNotFoundException;
import com.bank.transfer.exception.PhoneTransferValidatorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class PhoneTransferEntityControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PhoneTransferEntityNotFoundException.class)
    public ResponseEntity<String> handlePhoneTransferNotFoundException(PhoneTransferEntityNotFoundException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PhoneTransferValidatorException.class)
    public ResponseEntity<String> handleCardTransferException(PhoneTransferValidatorException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
