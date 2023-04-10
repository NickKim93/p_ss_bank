package com.bank.publicinfo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Table(name = "atm")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Atm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(nullable = false)
    private String address;

    @Column(name = "start_of_work")
    private LocalDateTime startOfWork;

    @Column(name = "end_of_work")
    private LocalDateTime endOfWork;

    @Column(name = "all_hours", nullable = false)
    private boolean allHours;

    @ManyToOne
    @JoinColumn(name = "branch_id", referencedColumnName = "id")
    @JsonIgnore
    private Branch branch;
}
