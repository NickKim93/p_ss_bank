package com.bank.profile.service;

import com.bank.profile.dto.ProfileDto;

import java.math.BigInteger;
import java.util.List;

public interface ProfileService {
    public List<ProfileDto> findAll();

    public ProfileDto findOne(BigInteger id);

    public ProfileDto findByInnOrPhoneNumber(Long n);

    public ProfileDto create(ProfileDto profileDto);

    public boolean delete(BigInteger id);

    public ProfileDto update(ProfileDto profileDto);
}