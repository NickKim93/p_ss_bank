package com.bank.transfer.dto;

import java.math.BigInteger;
/**
 * Класс, описывающий Data Transfer Objects Phone Transfer
 * @author Savenkov Artem
 */
public record PhoneTransferDto(BigInteger id,
                               BigInteger phoneNumber,
                               BigInteger amount,
                               String purpose,
                               BigInteger accountDetailsId) {
}
