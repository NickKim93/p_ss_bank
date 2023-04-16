package com.bank.antifraud.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Класс, описывающий сущность SuspiciousCardTransfer
 *
 * @author Makariy Petrov
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "suspicious_card_transfer")
public class SuspiciousCardTransferEntity extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "card_transfer_id",
            nullable = false, unique = true)
    Long cardTransferId;
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
