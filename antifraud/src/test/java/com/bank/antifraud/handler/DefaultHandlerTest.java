package com.bank.antifraud.handler;

import com.bank.antifraud.exception.IncorrectTransferTypeException;
import com.bank.antifraud.util.ApiError;
import org.hibernate.PropertyValueException;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@DisplayName("Обработчик ошибок")
class DefaultHandlerTest {

    @InjectMocks
    private DefaultHandler defaultHandler;

    @Test
    @DisplayName("EntityNotFoundException - HttpStatus.NOT_FOUND")
    public void handleEntityNotFoundException() {
        // given
        EntityNotFoundException ex = new EntityNotFoundException("Entity not found");

        // when
        ResponseEntity<ApiError> responseEntity = defaultHandler.handleEntityNotFoundException(ex);

        // then
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("Entity not found", responseEntity.getBody().getErrorMessage());
    }

    @Test
    @DisplayName("PropertyValueException - HttpStatus.BAD_REQUEST")
    public void handlePropertyValueException() {
        // given
        PropertyValueException ex = new PropertyValueException("property", "value", "message");

        // when
        ResponseEntity<ApiError> responseEntity = defaultHandler.handlePropertyValueException(ex);

        // then
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    @DisplayName("ConstraintViolationException - HttpStatus.BAD_REQUEST")
    public void handleConstraintViolationException() {
        // given
        ConstraintViolationException ex = new ConstraintViolationException("message", null, "constraint");

        // when
        ResponseEntity<ApiError> responseEntity = defaultHandler.handleConstraintViolationException(ex);

        //then
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("Нарушена уникальность значения constraint", responseEntity.getBody().getErrorMessage());
    }

    @Test
    @DisplayName("MethodArgumentNotValidException - HttpStatus.BAD_REQUEST")
    public void handleMethodArgumentNotValidException() {
        // given
        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);

        // when
        when(ex.getMessage()).thenReturn("error");
        ResponseEntity<ApiError> responseEntity = defaultHandler.handleValidationException(ex);

        //then
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    @DisplayName("IncorrectTransferTypeException - HttpStatus.INTERNAL_SERVER_ERROR")
    public void handleIncorrectTransferTypeException() {
        // given
        IncorrectTransferTypeException ex = mock(IncorrectTransferTypeException.class);

        // when
        ResponseEntity<ApiError> responseEntity = defaultHandler.handleIncorrectTransferTypeException(ex);

        //then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }
}