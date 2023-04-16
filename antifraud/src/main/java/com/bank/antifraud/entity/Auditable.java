package com.bank.antifraud.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
}
