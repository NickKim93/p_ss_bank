package com.bank.profile.repository;

import com.bank.profile.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, BigInteger> {
    Profile findProfileByInnOrPhoneNumber(Long inn, Long n);
}
