package com.bank.publicinfo.repository;

import com.bank.publicinfo.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface BranchRepository extends JpaRepository<Branch, Long> {
}
