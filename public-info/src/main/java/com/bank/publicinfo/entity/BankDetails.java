package com.bank.publicinfo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Set;

@Entity
@Table(name = "bank_details")
@Getter
@Setter
@NoArgsConstructor
public class BankDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "bik", nullable = false)
    private BigInteger bik;

    @Column(name = "inn", nullable = false)
    private BigInteger inn;

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

    @OneToMany(mappedBy = "bankDetails")
    @JsonIgnore
    private Set<Certificate> certificates;

    @OneToMany(mappedBy = "bankDetails")
    @JsonIgnore
    private Set<License> licenses;

}
