package com.bank.antifraud.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Родительский класс для всех сущностей подозрительных переводов
 *
 * @author Makariy Petrov
 */
@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class SuspiciousTransfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long transferId;
    @Column(name = "is_blocked",
            nullable = false)
    private Boolean isBlocked;
    @Column(name = "is_suspicious",
            nullable = false)
    private Boolean isSuspicious;
    @Column(name = "blocked_reason")
    private String blockedReason;
    @Column(name = "suspicious_reason",
            nullable = false)
    private String suspiciousReason;
}
