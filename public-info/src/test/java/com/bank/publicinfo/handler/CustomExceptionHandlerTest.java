package com.bank.publicinfo.handler;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomExceptionHandlerTest {

    @Mock
    private BindingResult bindingResult;

    @Test
    public void testHandleValidationExceptions() {
        // given
        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, bindingResult);
        FieldError fieldError = new FieldError("objectName", "fieldName", "errorMessage");
        when(bindingResult.getAllErrors()).thenReturn(Collections.singletonList(fieldError));
        CustomExceptionHandler customExceptionHandler1 = new CustomExceptionHandler();

        // when
        Map<String, String> result = customExceptionHandler1.handleValidationExceptions(ex);

        // then
        assertEquals(result.size(), 1);
        assertEquals(result.get("fieldName"), "errorMessage");
    }

}