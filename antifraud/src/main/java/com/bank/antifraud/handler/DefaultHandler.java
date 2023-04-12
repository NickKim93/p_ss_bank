package com.bank.antifraud.handler;

import org.hibernate.PropertyValueException;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

/**
 * Обработка стандартных ошибок для информативных ответов клиенту
 * @author Makariy Petrov
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class DefaultHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultHandler.class);
    /**
     * Ошибка, когда сущности по указанному  id не существует
     * @param ex информация об ошибке
     * @return сообщение, которое было передано
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
        LOGGER.info("Обрабатывается ошибка EntityNotFoundException");
        String message = ex.getMessage();
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    /**
     * Когда не передают значение, которое не может быть null
     * @param ex информация об ошибке
     * @return возвращает поле, которое не может быть равно null
     */
    @ExceptionHandler(PropertyValueException.class)
    public ResponseEntity<String> handlePropertyValueException(PropertyValueException ex) {
        LOGGER.info("Обрабатывается ошибка PropertyValueException");
        String message = "В запросе отсутствует или равно null обязятальное поле: " + ex.getPropertyName();
        return ResponseEntity.badRequest().body(message);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex) {
        LOGGER.info("Обрабатывается ошибка ConstraintViolationException");
        String message = "Нарушена уникальность значения " + ex.getConstraintName();
        return ResponseEntity.badRequest().body(message);
    }
}