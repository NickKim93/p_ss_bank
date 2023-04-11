package com.bank.publicinfo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "certificate")
@NoArgsConstructor
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "photo", nullable = false)
    private byte[] photo;

    @ManyToOne
    @JoinColumn(name = "bank_details_id", referencedColumnName = "id", nullable = false)
    private BankDetails bankDetails;

}
