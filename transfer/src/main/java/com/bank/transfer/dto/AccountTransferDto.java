package com.bank.transfer.dto;

import java.math.BigInteger;

/**
 * Класс, описывающий Data Transfer Objects Account Transfer
 * @author Savenkov Artem
 */
public record AccountTransferDto(BigInteger id,
                                 BigInteger accountNumber,
                                 BigInteger amount,
                                 String purpose,
                                 BigInteger accountDetailsId) {
}
