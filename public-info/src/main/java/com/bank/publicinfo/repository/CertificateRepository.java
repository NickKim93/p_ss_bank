package com.bank.publicinfo.repository;

import com.bank.publicinfo.entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface CertificateRepository extends JpaRepository<Certificate, BigInteger> {
}
