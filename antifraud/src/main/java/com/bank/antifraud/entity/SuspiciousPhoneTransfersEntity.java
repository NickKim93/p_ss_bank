package com.bank.antifraud.entity;

import com.bank.antifraud.util.Auditable;
import com.bank.antifraud.util.AuditingEntityListener;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "suspicious_phone_transfers")
public class SuspiciousPhoneTransfersEntity extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    BigInteger id;
    @Column(name = "phone_transfer_id",
            nullable = false, unique = true)
    Long phoneTransferId;
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
