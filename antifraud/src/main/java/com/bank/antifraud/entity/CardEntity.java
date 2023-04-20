package com.bank.antifraud.entity;

import com.bank.antifraud.aspect.Type;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Класс, описывающий подозрительные переводы по номеру карты
 *
 * @author Makariy Petrov
 */
@Getter
@Setter
@NoArgsConstructor
@Type("card")
@Entity
@Table(name = "suspicious_card_transfer")
@AttributeOverride(name = "transferId", column = @Column(name = "card_transfer_id", nullable = false, unique = true))
public class CardEntity extends SuspiciousTransfer {
}
