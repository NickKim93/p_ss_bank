package com.bank.publicinfo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "branch")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String address;

    @Column(name = "phone_number", nullable = false)
    private Long phoneNumber;
    @Column(nullable = false)
    private String city;
    @Column(name = "start_of_work", nullable = false)
    private String startOfWork;

    @Column(name = "end_of_work", nullable = false)
    private String endOfWork;

    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Atm> atmList;
}
