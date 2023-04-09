package com.bank.publicinfo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "atm")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Atm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(nullable = false)
    private String address;

    @Column(name = "start_of_work")
    private String startOfWork;

    @Column(name = "end_of_work")
    private String endOfWork;

    @Column(name = "all_hours", nullable = false)
    private boolean allHours;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", referencedColumnName = "id")
    private Branch branch;
}
