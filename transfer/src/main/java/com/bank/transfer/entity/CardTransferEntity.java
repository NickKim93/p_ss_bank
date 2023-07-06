package com.bank.transfer.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "card_transfer", schema = "transfer")
public class CardTransferEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Positive
    @Column(name = "card_number", nullable = false, unique = true)
    private Long cardNumber;
    @Column(name = "amount", precision = 20, scale = 2, nullable = false)
    private BigDecimal amount;
    @Column(name = "purpose", nullable = false)
    private String purpose;
    @Positive
    @Column(name = "account_details_id" , nullable = false)
    private Long accountDetailsId;
}
