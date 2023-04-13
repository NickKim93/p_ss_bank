package com.bank.transfer.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.math.BigInteger;

/**
 * Класс, описывающий сущность Card Transfer
 * @author Savenkov Artem
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "card_transfer")
public class CardTransferEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private BigInteger id;
    @Positive
    @Column(name = "card_number", nullable = false)
    private BigInteger cardNumber;
    @Column(name = "amount", precision = 20, scale = 2, nullable = false)
    private BigInteger amount;
    @Column(name = "purpose", nullable = false)
    private String purpose;
    @Positive
    @Column(name = "account_details_id" , nullable = false)
    private BigInteger accountDetailsId;
}
