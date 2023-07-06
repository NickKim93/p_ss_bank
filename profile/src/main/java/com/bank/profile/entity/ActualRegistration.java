package com.bank.profile.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Сущность адреса текущего места проживания
 * является необязательной
 */
@Entity
@Table(name = "actual_registration")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@EqualsAndHashCode
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
    @Min(value = 100000L)
    @Max(value = 999999L)
    private Long index;

    @OneToOne(mappedBy = "actualRegistration")
    @JsonBackReference
    private Profile profile;
}
