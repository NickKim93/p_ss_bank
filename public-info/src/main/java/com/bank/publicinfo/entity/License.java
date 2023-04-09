package com.bank.publicinfo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Data
@Table(name = "license")
@NoArgsConstructor
public class License {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private BigInteger id;

    @Column(name = "photo", nullable = false)
    private byte[] photo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_details_id", referencedColumnName = "id", nullable = false)
    private BankDetails bankDetails;

    @Version
    private Long version;
}
