package com.bank.profile.exception;

import lombok.Getter;

/**
 * BadRequestException - исключение выкидывается, если за прос является некорректным
 * */
@Getter
public class BadRequestException extends RuntimeException {
    private final String message;

    public BadRequestException(String message) {
        super(message);
        this.message = message;
    }
}
