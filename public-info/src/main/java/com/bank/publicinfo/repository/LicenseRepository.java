package com.bank.publicinfo.repository;

import com.bank.publicinfo.entity.License;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LicenseRepository extends JpaRepository<License, Long> {
}
