package com.bank.transfer.exception;
public class AccountTransferValidatorException extends RuntimeException {
    public AccountTransferValidatorException(String message) {
        super(message);
    }
}
