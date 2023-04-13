package com.bank.transfer.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.math.BigInteger;

/**
 * Класс, описывающий сущность Phone Transfer
 * @author Savenkov Artem
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "phone_transfer")
public class PhoneTransferEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private BigInteger id;
    @Positive
    @Column(name = "phone_number", nullable = false)
    private BigInteger phoneNumber;
    @Column(name = "amount", precision = 20, scale = 2, nullable = false)
    private BigInteger amount;
    @Column(name = "purpose", nullable = false)
    private String purpose;
    @Positive
    @Column(name = "account_details_id" , nullable = false)
    private BigInteger accountDetailsId;
}
