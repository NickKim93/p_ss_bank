package com.bank.profile.handler;

import com.bank.profile.exception.BadRequestException;
import com.bank.profile.exception.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Контроллер обработчика исключений
 * */
@RestControllerAdvice
@Slf4j
public class HandlerException {
    @ResponseBody
    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<?> handlerBadRequestException(BadRequestException exception) {
        log.error(exception.getMessage().concat(" [" + exception.getStackTrace()[0] + "]"));
        Map<String, String> error = new HashMap<>();
        error.put("errorMessage", exception.getMessage());
        return ResponseEntity
                .badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(error);
    }
    @ResponseBody
    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<?> handlerNotFoundEntity(EntityNotFoundException exception) {
        log.error(exception.getMessage().concat(" [" + exception.getStackTrace()[0] + "]"));
        return ResponseEntity
                .notFound()
                .build();
    }
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        log.error(exception.getMessage().concat(" [" + exception.getStackTrace()[0] + "]"));
        return ResponseEntity
                .badRequest()
                .body(errors);
    }
}
