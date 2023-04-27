package com.bank.transfer.exception;
public class CardTransferEntityNotFoundException extends RuntimeException {
    public CardTransferEntityNotFoundException(String message) {
        super(message);
    }
}
