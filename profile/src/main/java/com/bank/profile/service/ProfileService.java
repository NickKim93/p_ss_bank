package com.bank.profile.service;

import com.bank.profile.dto.ProfileDto;

import java.util.List;

public interface ProfileService {
    List<ProfileDto> findAll();

    ProfileDto findOne(Long id);

    ProfileDto findByInnOrPhoneNumber(Long n);

    ProfileDto create(ProfileDto profileDto);

    void delete(Long id);

    ProfileDto update(ProfileDto profileDto);
}
