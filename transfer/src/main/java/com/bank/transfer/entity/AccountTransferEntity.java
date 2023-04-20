package com.bank.transfer.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "account_transfer", schema = "transfer")
public class AccountTransferEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Positive
    @Column(name = "account_number", nullable = false, unique = true)
    private Long accountNumber;
    @Column(name = "amount", precision = 20, scale = 2, nullable = false)
    private BigDecimal amount;
    @Column(name = "purpose", nullable = false)
    private String purpose;
    @Positive
    @Column(name = "account_details_id" , nullable = false)
    private Long accountDetailsId;
}
