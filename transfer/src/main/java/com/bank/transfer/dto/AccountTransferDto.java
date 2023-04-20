package com.bank.transfer.dto;

import java.math.BigDecimal;

public record AccountTransferDto(Long id,
                                 Long accountNumber,
                                 BigDecimal amount,
                                 String purpose,
                                 Long accountDetailsId) {
}
