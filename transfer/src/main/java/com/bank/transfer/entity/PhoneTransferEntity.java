package com.bank.transfer.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "phone_transfer", schema = "transfer")
public class PhoneTransferEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Positive
    @Column(name = "phone_number", nullable = false)
    private Long phoneNumber;
    @Column(name = "amount", precision = 20, scale = 2, nullable = false)
    private BigDecimal amount;
    @Column(name = "purpose", nullable = false)
    private String purpose;
    @Positive
    @Column(name = "account_details_id" , nullable = false)
    private Long accountDetailsId;
}
