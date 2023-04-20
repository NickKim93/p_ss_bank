package com.bank.antifraud.handler;

import com.bank.antifraud.exception.IncorrectTransferTypeException;
import com.bank.antifraud.util.ApiError;
import org.hibernate.PropertyValueException;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Order;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

/**
 * Обработка стандартных ошибок для информативных ответов клиенту
 *
 * @author Makariy Petrov
 */
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class DefaultHandler {
    /**
     * Ошибка, когда сущности по указанному  id не существует
     *
     * @param ex информация об ошибке
     * @return сообщение, которое было передано
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiError> handleEntityNotFoundException(EntityNotFoundException ex) {
        return new ResponseEntity<>(new ApiError(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    /**
     * Когда не передают значение, которое не может быть null
     *
     * @param ex информация об ошибке
     * @return возвращает поле, которое не может быть равно null
     */
    @ExceptionHandler(PropertyValueException.class)
    public ResponseEntity<ApiError> handlePropertyValueException(PropertyValueException ex) {
        String message = "В запросе отсутствует или равно null обязятальное поле: " + ex.getPropertyName();
        return ResponseEntity.badRequest().body(new ApiError(message));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError> handleConstraintViolationException(ConstraintViolationException ex) {
        String message = "Нарушена уникальность значения " + ex.getConstraintName();
        return ResponseEntity.badRequest().body(new ApiError(message));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationException(MethodArgumentNotValidException ex) {
        String message = "Ошибка валидации входящих значений: " + ex.getMessage();
        return ResponseEntity.badRequest().body(new ApiError(message));
    }

    @ExceptionHandler(IncorrectTransferTypeException.class)
    public ResponseEntity<ApiError> handleIncorrectTransferTypeException(IncorrectTransferTypeException ex) {
        return ResponseEntity.internalServerError().body(new ApiError("Не удалось определить тип сущности"));
    }
}