package com.bank.publicinfo.repository;

import com.bank.publicinfo.entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {
}
