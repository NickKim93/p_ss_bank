package com.bank.publicinfo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name = "bank_details")
@Data
@NoArgsConstructor
public class BankDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private BigInteger id;

    @Column(name = "bik", nullable = false)
    private BigInteger bik;

    @Column(name = "kpp", nullable = false)
    private BigInteger kpp;

    @Column(name = "cor_account", nullable = false)
    private Integer corAccount;

    @Column(name = "city", nullable = false, length = 180)
    private String city;

    @Column(name = "joint_stock_company", nullable = false, length = 15)
    private String jointStockCompany;

    @Column(name = "name", nullable = false, length = 80)
    private String name;

    @Version
    private Long version;

    @OneToMany(mappedBy = "bankDetails", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Certificate> certificates;

    @OneToMany(mappedBy = "bankDetails", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<License> licenses;

}
