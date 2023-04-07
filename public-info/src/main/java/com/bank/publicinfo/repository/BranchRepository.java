package com.bank.publicinfo.repository;

import com.bank.publicinfo.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Branch, Long> {
}
