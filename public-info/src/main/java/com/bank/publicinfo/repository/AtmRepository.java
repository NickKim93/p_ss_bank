package com.bank.publicinfo.repository;

import com.bank.publicinfo.entity.Atm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtmRepository extends JpaRepository<Atm, Long> {
}
