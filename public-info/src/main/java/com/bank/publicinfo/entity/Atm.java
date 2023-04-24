package com.bank.publicinfo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
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
    private Long id;

    @Column(nullable = false)
    private String address;

    @Column(name = "start_of_work", nullable = false)
    private LocalDateTime startOfWork;

    @Column(name = "end_of_work", nullable = false)
    private LocalDateTime endOfWork;

    @Column(name = "all_hours", nullable = false)
    private boolean allHours;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", referencedColumnName = "id")
    private Branch branch;
}
