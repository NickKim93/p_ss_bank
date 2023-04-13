package com.bank.transfer.dto;

import java.math.BigInteger;
/**
 * Класс, описывающий Data Transfer Objects Card Transfer
 * @author Savenkov Artem
 */
public record CardTransferDto(BigInteger id,
                              BigInteger cardNumber,
                              BigInteger amount,
                              String purpose,
                              BigInteger accountDetailsId) {
}
