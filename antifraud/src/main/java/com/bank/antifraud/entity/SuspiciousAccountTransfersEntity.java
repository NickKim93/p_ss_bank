package com.bank.antifraud.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "suspicious_account_transfers")
@SecondaryTable(name = "audit")
public class SuspiciousAccountTransfersEntity extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
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
