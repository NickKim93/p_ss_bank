package com.bank.profile.handler;

import com.bank.profile.exception.BadRequestException;
import com.bank.profile.exception.EntityNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
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
 */
@RestControllerAdvice
@Slf4j
public class HandlerException {
    @ResponseBody
    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<?> handlerBadRequestException(BadRequestException exception) {
        log.error("{} [{}]", exception.getMessage(), exception.getStackTrace()[0]);
        final Map<String, String> error = new HashMap<>();
        error.put("errorMessage", exception.getMessage());
        return ResponseEntity
                .badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(error);
    }

    @ResponseBody
    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<?> handlerNotFoundEntity(EntityNotFoundException exception) {
        log.error("{} [{}]", exception.getMessage(), exception.getStackTrace()[0]);
        return ResponseEntity
                .notFound()
                .build();
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException exception) {
        final Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            final String fieldName = ((FieldError) error).getField();
            final String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        log.error("{} [{}]", exception.getMessage(), exception.getStackTrace()[0]);
        return ResponseEntity
                .badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(errors);
    }

    @ExceptionHandler(JsonProcessingException.class)
    public void handleJsonProcessingExceptions(JsonProcessingException exception) {
        log.error("Ошибка преобразования Entity в JSON для записи в таблицу аудита [{}]",
                exception.getStackTrace()[0]);
        log.error(String.valueOf(exception));
    }
}
