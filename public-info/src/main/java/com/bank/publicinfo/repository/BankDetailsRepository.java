package com.bank.publicinfo.repository;

import com.bank.publicinfo.entity.BankDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface BankDetailsRepository extends JpaRepository<BankDetails, Long> {
}
