package com.bank.antifraud.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * DTO для работы с подозрительными переводами
 *
 * @author Makariy Petrov
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SuspiciousTransferDto {
    private String type;
    private Long id;
    @NotNull
    private Long transferId;
    private Boolean isBlocked;
    private Boolean isSuspicious;
    private String blockedReason;
    @NotBlank
    private String suspiciousReason;
}