package com.bank.transfer.dto;

import java.math.BigDecimal;
public record PhoneTransferDto(Long id,
                               Long phoneNumber,
                               BigDecimal amount,
                               String purpose,
                               Long accountDetailsId) {
}
