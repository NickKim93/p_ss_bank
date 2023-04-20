package com.bank.account.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
@Getter
@Setter
@AllArgsConstructor
public class AccountDetailsErrorResponse {
    private String message;
    private OffsetDateTime errorTime;
}
