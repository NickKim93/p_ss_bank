package com.bank.profile.service;

import com.bank.profile.dto.ProfileDto;
import com.bank.profile.entity.*;
import com.bank.profile.repository.AccountDetailsRepository;
import com.bank.profile.repository.ProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final AccountDetailsRepository accountDetailsRepository;

    public ProfileService(ProfileRepository profileRepository, AccountDetailsRepository accountDetailsRepository) {
        this.profileRepository = profileRepository;
        this.accountDetailsRepository = accountDetailsRepository;
    }

    @Transactional(readOnly = true)
    public List<ProfileDto> findAll() {
        return profileRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProfileDto findOne(Long id) {
        Profile profile = profileRepository.findById(id).orElse(null);
        return profile == null ? null : convertEntityToDto(profile);
    }

    @Transactional(readOnly = true)
    public ProfileDto findByInnOrPhoneNumber(Long n) {
        Profile profile = profileRepository.findProfileByInnOrPhoneNumber(n, n);
        return profile == null ? null : convertEntityToDto(profile);
    }

    @Transactional
    public ProfileDto create(ProfileDto profileDto) {
        if (profileRepository.findProfileByInnOrPhoneNumber(profileDto.getInn(), profileDto.getPhoneNumber()) != null) {
            return null;
        }

        Profile profile = Profile.builder()
                .phoneNumber(profileDto.getPhoneNumber())
                .email(profileDto.getEmail())
                .nameOnCard(profileDto.getNameOnCard())
                .inn(profileDto.getInn())
                .snils(profileDto.getSnils())
                .build();
        Passport passport = Passport.builder()
                .series(profileDto.getSeries())
                .number(profileDto.getNumber())
                .lastName(profileDto.getLastName())
                .firstName(profileDto.getFirstName())
                .middleName(profileDto.getMiddleName())
                .gender(profileDto.getGender())
                .birthDate(profileDto.getBirthDate())
                .birthPlace(profileDto.getBirthPlace())
                .issuedBy(profileDto.getIssuedBy())
                .dateOfIssue(profileDto.getDateOfIssue())
                .divisionCode(profileDto.getDivisionCode())
                .expirationDate(profileDto.getExpirationDate())
                .build();
        Registration registration = Registration.builder()
                .country(profileDto.getCountry())
                .region(profileDto.getRegion())
                .city(profileDto.getCity())
                .district(profileDto.getDistrict())
                .locality(profileDto.getLocality())
                .street(profileDto.getStreet())
                .houseNumber(profileDto.getHouseNumber())
                .houseBlock(profileDto.getHouseBlock())
                .flatNumber(profileDto.getFlatNumber())
                .index(profileDto.getIndex())
                .build();
        passport.setRegistration(registration);
        profile.setPassport(passport);

        if (profileDto.getCountryAR() != null && profileDto.getIndexAR() != null) {
            ActualRegistration actualRegistration = ActualRegistration.builder()
                    .country(profileDto.getCountryAR())
                    .region(profileDto.getRegionAR())
                    .city(profileDto.getCityAR())
                    .district(profileDto.getDistrictAR())
                    .locality(profileDto.getLocalityAR())
                    .street(profileDto.getStreetAR())
                    .houseNumber(profileDto.getHouseNumberAR())
                    .houseBlock(profileDto.getHouseBlockAR())
                    .flatNumber(profileDto.getFlatNumberAR())
                    .index(profileDto.getIndexAR())
                    .build();
            profile.setActualRegistration(actualRegistration);
        }

        Profile profileLocal = profileRepository.save(profile);
        profileDto.setId(profileLocal.getId());
        profileDto.setPassportId(profileLocal.getPassport().getId());
        profileDto.setRegistrationId(profileLocal.getPassport().getRegistration().getId());
        profileDto.setActualRegistrationId(profileLocal.getActualRegistration() == null ? null : profileLocal.getActualRegistration().getId());
        return profileDto;
    }

    @Transactional
    public boolean delete(Long id) {
        if (!profileRepository.existsById(id)) {
            return false;
        }
        profileRepository.deleteById(id);
        return true;
    }

    @Transactional
    public ProfileDto update(ProfileDto profileDto) {
        Profile profile = profileRepository.findById(profileDto.getId()).orElse(null);
        if (profile == null || !Objects.equals(profile.getInn(), profileDto.getInn()) || !Objects.equals(profile.getSnils(), profileDto.getSnils())) {
            return null;
        }
        return convertEntityToDto(
                profileRepository.save(
                        convertDtoToEntity(profileDto, profile)
                ));
    }

    private ProfileDto convertEntityToDto(Profile profile) {
        return ProfileDto.builder()
                .id(profile.getId())
                .phoneNumber(profile.getPhoneNumber())
                .email(profile.getEmail())
                .nameOnCard(profile.getNameOnCard())
                .inn(profile.getInn())
                .snils(profile.getSnils())
                .passportId(profile.getPassport().getId())
                .actualRegistrationId(profile.getActualRegistration() == null ? 0 : profile.getActualRegistration().getId())
                .accountDetailsId(profile.getAccountDetailsSet().stream().map(AccountDetails::getId).collect(Collectors.toSet()))

                .series(profile.getPassport().getSeries())
                .number(profile.getPassport().getNumber())
                .lastName(profile.getPassport().getLastName())
                .firstName(profile.getPassport().getFirstName())
                .middleName(profile.getPassport().getMiddleName())
                .gender(profile.getPassport().getGender())
                .birthDate(profile.getPassport().getBirthDate())
                .birthPlace(profile.getPassport().getBirthPlace())
                .issuedBy(profile.getPassport().getIssuedBy())
                .dateOfIssue(profile.getPassport().getDateOfIssue())
                .divisionCode(profile.getPassport().getDivisionCode())
                .expirationDate(profile.getPassport().getExpirationDate())
                .registrationId(profile.getPassport().getRegistration().getId())

                .country(profile.getPassport().getRegistration().getCountry())
                .region(profile.getPassport().getRegistration().getRegion())
                .city(profile.getPassport().getRegistration().getCity())
                .district(profile.getPassport().getRegistration().getDistrict())
                .locality(profile.getPassport().getRegistration().getLocality())
                .street(profile.getPassport().getRegistration().getStreet())
                .houseNumber(profile.getPassport().getRegistration().getHouseNumber())
                .houseBlock(profile.getPassport().getRegistration().getHouseBlock())
                .flatNumber(profile.getPassport().getRegistration().getFlatNumber())
                .index(profile.getPassport().getRegistration().getIndex())

                .countryAR(profile.getActualRegistration() == null ? null : profile.getActualRegistration().getCountry())
                .regionAR(profile.getActualRegistration() == null ? null : profile.getActualRegistration().getRegion())
                .cityAR(profile.getActualRegistration() == null ? null : profile.getActualRegistration().getCity())
                .districtAR(profile.getActualRegistration() == null ? null : profile.getActualRegistration().getDistrict())
                .localityAR(profile.getActualRegistration() == null ? null : profile.getActualRegistration().getLocality())
                .streetAR(profile.getActualRegistration() == null ? null : profile.getActualRegistration().getStreet())
                .houseNumberAR(profile.getActualRegistration() == null ? null : profile.getActualRegistration().getHouseNumber())
                .houseBlockAR(profile.getActualRegistration() == null ? null : profile.getActualRegistration().getHouseBlock())
                .flatNumberAR(profile.getActualRegistration() == null ? null : profile.getActualRegistration().getFlatNumber())
                .indexAR(profile.getActualRegistration() == null ? null : profile.getActualRegistration().getIndex())

                .build();
    }

    private Profile convertDtoToEntity(ProfileDto profileDto, Profile profile) {
        profile.setPhoneNumber(profileDto.getPhoneNumber());
        profile.setEmail(profileDto.getEmail());
        profile.setNameOnCard(profileDto.getNameOnCard());
        profile.setInn(profileDto.getInn());
        profile.setSnils(profileDto.getSnils());

        profile.setAccountDetailsSet(profileDto.getAccountDetailsId().stream()
                .map(aLong -> accountDetailsRepository.findById(aLong).orElse(null))
                .collect(Collectors.toSet())
        );

        Passport passport = profile.getPassport();
        passport.setSeries(profileDto.getSeries());
        passport.setNumber(profileDto.getNumber());
        passport.setLastName(profileDto.getLastName());
        passport.setFirstName(profileDto.getFirstName());
        passport.setMiddleName(profileDto.getMiddleName());
        passport.setGender(profileDto.getGender());
        passport.setBirthDate(profileDto.getBirthDate());
        passport.setBirthPlace(profileDto.getBirthPlace());
        passport.setIssuedBy(profileDto.getIssuedBy());
        passport.setDateOfIssue(profileDto.getDateOfIssue());
        passport.setDivisionCode(profileDto.getDivisionCode());
        passport.setExpirationDate(profileDto.getExpirationDate());

        Registration registration = passport.getRegistration();
        registration.setCountry(profileDto.getCountry());
        registration.setRegion(profileDto.getRegion());
        registration.setCity(profileDto.getCity());
        registration.setDistrict(profileDto.getDistrict());
        registration.setLocality(profileDto.getLocality());
        registration.setStreet(profileDto.getStreet());
        registration.setHouseNumber(profileDto.getHouseNumber());
        registration.setHouseBlock(profileDto.getHouseBlock());
        registration.setFlatNumber(profileDto.getFlatNumber());
        registration.setIndex(profileDto.getIndex());

        if (profileDto.getCountryAR() != null
                && !profileDto.getCountryAR().isBlank()
                && profileDto.getIndexAR() != null
                && profileDto.getIndexAR() != 0) {
            ActualRegistration actualRegistration = profile.getActualRegistration() == null ? new ActualRegistration() : profile.getActualRegistration();
            actualRegistration.setCountry(profileDto.getCountryAR());
            actualRegistration.setRegion(profileDto.getRegionAR());
            actualRegistration.setCity(profileDto.getCityAR());
            actualRegistration.setDistrict(profileDto.getDistrictAR());
            actualRegistration.setLocality(profileDto.getLocalityAR());
            actualRegistration.setStreet(profileDto.getStreetAR());
            actualRegistration.setHouseNumber(profileDto.getHouseNumberAR());
            actualRegistration.setHouseBlock(profileDto.getHouseBlockAR());
            actualRegistration.setFlatNumber(profileDto.getFlatNumberAR());
            actualRegistration.setIndex(profileDto.getIndexAR());

            profile.setActualRegistration(actualRegistration);
        } else {
            profile.setActualRegistration(null);
        }

        return profile;
    }
}
