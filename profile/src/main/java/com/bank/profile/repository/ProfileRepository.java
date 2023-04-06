package com.bank.profile.repository;

import com.bank.profile.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Profile findProfileByInnOrPhoneNumber(Long inn, Long n);
}
