package com.bank.profile.handler;

import com.bank.profile.exception.BadRequestException;
import com.bank.profile.exception.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class HandlerException {
    @ResponseBody
    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<?> handlerBadRequest(BadRequestException exception) {
        log.error(exception.getMessage());
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
    @ResponseBody
    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<?> handlerNotFoundEntity(EntityNotFoundException exception) {
        log.warn(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
}
