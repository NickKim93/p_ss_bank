package com.bank.antifraud.handler;

import org.hibernate.PropertyValueException;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class DefaultHandlerTest {

    @InjectMocks
    private DefaultHandler defaultHandler;

    @Test
    public void handleEntityNotFoundException() {
        EntityNotFoundException ex = new EntityNotFoundException("Entity not found");
        ResponseEntity<String> responseEntity = defaultHandler.handleEntityNotFoundException(ex);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Entity not found", responseEntity.getBody());
    }

    @Test
    public void handlePropertyValueException() {
        PropertyValueException ex = new PropertyValueException("property", "value", "message");
        ResponseEntity<String> responseEntity = defaultHandler.handlePropertyValueException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void handleConstraintViolationException() {
        ConstraintViolationException ex = new ConstraintViolationException("message", null, "constraint");
        ResponseEntity<String> responseEntity = defaultHandler.handleConstraintViolationException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Нарушена уникальность значения constraint", responseEntity.getBody());
    }
}