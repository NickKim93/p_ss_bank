package com.bank.profile.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "actual_registration")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class ActualRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "country")
    @NotNull
    @NotBlank
    @Size(max = 40)
    private String country;
    @Column(name = "region")
    @Size(max = 160)
    private String region;
    @Column(name = "city")
    @Size(max = 160)
    private String city;
    @Column(name = "district")
    @Size(max = 160)
    private String district;
    @Column(name = "locality")
    @Size(max = 230)
    private String locality;
    @Column(name = "street")
    @Size(max = 230)
    private String street;
    @Column(name = "house_number")
    @Size(max = 20)
    private String houseNumber;
    @Column(name = "house_block")
    @Size(max = 20)
    private String houseBlock;
    @Column(name = "flat_number")
    @Size(max = 40)
    private String flatNumber;
    @Column(name = "index")
    @NotNull
    @NotBlank
    @Size(min = 6, max = 6)
    private Long index;
    @OneToOne(mappedBy = "actualRegistration")
    private Profile profile;
}
