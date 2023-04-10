package com.bank.publicinfo.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "branch")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;
    @Column(nullable = false)
    private String address;

    @Column(name = "phone_number", nullable = false)
    private BigInteger phoneNumber;
    @Column(nullable = false)
    private String city;
    @Column(name = "start_of_work", nullable = false)
    private LocalDateTime startOfWork;

    @Column(name = "end_of_work", nullable = false)
    private LocalDateTime endOfWork;

    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Atm> atmList;
}
