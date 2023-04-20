package com.bank.antifraud.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Класс для представление ошибки в формате json
 *
 * @author Makariy Petrov
 */
@AllArgsConstructor
@Getter
@Setter
public class ApiError {
    private String errorMessage;
}
