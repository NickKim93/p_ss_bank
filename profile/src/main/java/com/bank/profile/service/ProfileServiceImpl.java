package com.bank.profile.service;

import com.bank.profile.dto.ProfileDto;
import com.bank.profile.entity.*;
import com.bank.profile.mapper.ProfileMapper;
import com.bank.profile.repository.ProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;

    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProfileDto> findAll() {
        return profileRepository.findAll()
                .stream()
                .map(ProfileMapper.INSTANCE::profileToProfileDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProfileDto findOne(Long id) {
        Profile profile = profileRepository.findById(id).orElse(null);
        return profile == null ? null : ProfileMapper.INSTANCE.profileToProfileDto(profile);
    }

    @Override
    @Transactional(readOnly = true)
    public ProfileDto findByInnOrPhoneNumber(Long n) {
        Profile profile = profileRepository.findProfileByInnOrPhoneNumber(n, n);
        return profile == null ? null : ProfileMapper.INSTANCE.profileToProfileDto(profile);
    }

    @Override
    @Transactional
    public ProfileDto create(ProfileDto profileDto) {
        if (profileRepository.findProfileByInnOrPhoneNumber(profileDto.getInn(), profileDto.getPhoneNumber()) != null) {
            return null;
        }

        Profile profile = ProfileMapper.INSTANCE.profileDtoToProfile(profileDto);
        Profile profileLocal = profileRepository.save(profile);
        return ProfileMapper.INSTANCE.profileToProfileDto(profileLocal);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        if (!profileRepository.existsById(id)) {
            return false;
        }
        profileRepository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public ProfileDto update(ProfileDto profileDto) {
        Profile profile = profileRepository.findById(profileDto.getId()).orElse(null);
        if (profile == null || !Objects.equals(profile.getInn(), profileDto.getInn()) || !Objects.equals(profile.getSnils(), profileDto.getSnils())) {
            return null;
        }
        return ProfileMapper.INSTANCE.profileToProfileDto(
                profileRepository.save(
                        ProfileMapper.INSTANCE.profileDtoToProfile(profileDto)
                ));
    }
}
