package com.bank.profile.repository;

import com.bank.profile.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс доступа к базе данных
 */
@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Profile findProfileByInn(Long inn);

    Profile findProfileByPhoneNumber(Long n);

    Profile findProfileByInnOrPhoneNumber(Long inn, Long n);
}
