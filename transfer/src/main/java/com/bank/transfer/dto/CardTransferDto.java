package com.bank.transfer.dto;

import java.math.BigDecimal;
public record CardTransferDto(Long id,
                              Long cardNumber,
                              BigDecimal amount,
                              String purpose,
                              Long accountDetailsId) {
}
