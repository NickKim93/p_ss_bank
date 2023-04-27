package com.bank.transfer.exception;
public class AuditEntityNotFoundException extends RuntimeException{
    public AuditEntityNotFoundException(String message) {
        super(message);
    }
}
