package com.bank.antifraud.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "suspicious_account_transfers")
public class SuspiciousAccountTransfersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    BigInteger id;
    @Column(name = "account_transfer_id",
            nullable = false, unique = true)
    Long accountTransferId;
    @Column(name = "is_blocked",
            nullable = false)
    Boolean isBlocked;
    @Column(name = "is_suspicious",
            nullable = false)
    Boolean isSuspicious;
    @Column(name = "blocked_reason")
    String blockedReason;
    @Column(name = "suspicious_reason",
            nullable = false)
    String suspiciousReason;
}
