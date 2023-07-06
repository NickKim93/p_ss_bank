package com.bank.profile.handler;

import com.bank.profile.exception.BadRequestException;
import com.bank.profile.exception.EntityNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.MapBindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class HandlerExceptionTest {

    @InjectMocks
    HandlerException handlerException;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Обработка BadRequestException, проверка на валидность ResponseEntity")
    void handlerBadRequestException_ReturnsValidResponseEntity() {
        // given
        var message = "test message";
        Map<String, String> errorMessage = new HashMap<>();
        errorMessage.put("errorMessage", message);

        // when
        var responseEntity = handlerException.handlerBadRequestException(new BadRequestException(message));

        // then
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, responseEntity.getHeaders().getContentType());
        assertEquals(errorMessage, responseEntity.getBody());
    }

    @Test
    @DisplayName("Обработка NotFoundEntityException, проверка на валидность ResponseEntity")
    void handlerNotFoundEntityException_ReturnsValidResponseEntity() {
        // given
        var message = "test message";

        // when
        var responseEntity = handlerException.handlerNotFoundEntity(new EntityNotFoundException(message));

        // then
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    @DisplayName("Обработка MethodArgumentNotValidException, проверка на валидность ResponseEntity")
    void handleValidationExceptions_ReturnsValidResponseEntity() throws NoSuchMethodException {
        // given
        var message = "test message";
        Map<String, String> errorMessage = new HashMap<>();
        errorMessage.put("errorMessage", message);

        BindingResult bindingResult = new MapBindingResult(new HashMap<>(), "objectName");
        bindingResult.addError(new FieldError("objectName", "errorMessage", "test message"));

        Method method = this.getClass().getDeclaredMethod("handleValidationExceptions_ReturnsValidResponseEntity", (Class<?>[]) null);
        var methodParameter = new MethodParameter(method, -1);

        var exception = new MethodArgumentNotValidException(methodParameter, bindingResult);

        // when
        var responseEntity = handlerException.handleValidationExceptions(exception);

        // then
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, responseEntity.getHeaders().getContentType());
        assertEquals(errorMessage, responseEntity.getBody());
    }
}