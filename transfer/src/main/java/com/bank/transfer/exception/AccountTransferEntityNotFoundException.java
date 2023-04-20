package com.bank.transfer.exception;
public class AccountTransferEntityNotFoundException extends RuntimeException {
    public AccountTransferEntityNotFoundException(String message) {
        super(message);
    }
}
