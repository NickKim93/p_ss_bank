package com.bank.profile.service;

import com.bank.profile.dto.ProfileDto;
import com.bank.profile.entity.*;
import com.bank.profile.exception.BadRequestException;
import com.bank.profile.exception.EntityNotFoundException;
import com.bank.profile.mapper.ProfileMapper;
import com.bank.profile.repository.ProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * Сервисный слой, обеспечивающий обмен данными между REST контроллером и БД
 * */
@Service
@Slf4j
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;

    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    /**
     * Получение списка всех профилей
     * */
    @Override
    @Transactional(readOnly = true)
    public List<ProfileDto> findAll() {
        log.info("Request method findAll() успешно выполнен");
        return profileRepository.findAll()
                .stream()
                .map(ProfileMapper.INSTANCE::profileToProfileDto)
                .collect(Collectors.toList());
    }

    /**
     * Получение профиля по id
     * */
    @Override
    @Transactional(readOnly = true)
    public ProfileDto findOne(Long id) {
        if (id < 0) throw new BadRequestException("Значение id должно быть больше 0");

        Profile profile = profileRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Профиль с id=%s не найден", id)));
        log.info("Request method findOne(id={}) успешно выполнен", id);
        return ProfileMapper.INSTANCE.profileToProfileDto(profile);
    }

    /**
     * Получение профиля по ИНН или номеру телефона
     * */
    @Override
    @Transactional(readOnly = true)
    public ProfileDto findByInnOrPhoneNumber(Long n) {
        if (n < 0) {
            throw new BadRequestException(String.format("Значение ИНН или номера телефона (%s) должно быть больше 0", n));
        }

        Profile profile = profileRepository.findProfileByInnOrPhoneNumber(n, n);

        if (profile == null) {
            throw new EntityNotFoundException(String.format("Профиль с ИНН или номером телефона (%s) не найден", n));
        }

        log.info("Request method findByInnOrPhoneNumber(n={}) успешно выполнен", n);
        return ProfileMapper.INSTANCE.profileToProfileDto(profile);
    }

    /**
     * Создание нового профиля
     * */
    @Override
    @Transactional
    public ProfileDto create(ProfileDto profileDto) {
        if (profileDto.getInn() != null && (profileRepository.findProfileByInn(profileDto.getInn()) != null)
                || profileRepository.findProfileByPhoneNumber(profileDto.getPhoneNumber()) != null) {
            throw new BadRequestException(String.format("Request method create(). Профиль с ИНН = %s или номером телефона = %s уже существует", profileDto.getInn(), profileDto.getPhoneNumber()));
        }

        Profile profile = ProfileMapper.INSTANCE.profileDtoToProfile(profileDto);
        Profile profileLocal = profileRepository.save(profile);
        log.info("Request method create() успешно выполнен");
        return ProfileMapper.INSTANCE.profileToProfileDto(profileLocal);
    }

    /**
     * Удаление профиля по id
     * */
    @Override
    @Transactional
    public void delete(Long id) {
        if (!profileRepository.existsById(id)) {
            throw new EntityNotFoundException(String.format("Request method delete(id=%s). Профиль не найден", id));
        }

        profileRepository.deleteById(id);
        log.info("Request method delete(id={}). Запись успешно удалена", id);
    }

    /**
     * Обновление данных профиля
     * */
    @Override
    @Transactional
    public ProfileDto update(ProfileDto profileDto) {
        Profile profileLocal = profileRepository.findById(profileDto.getId())
                .orElseThrow(() -> new EntityNotFoundException(String.format("Request method update(id=%s). Профиль не найден", profileDto.getId())));

        if ((profileLocal.getInn() != null && !Objects.equals(profileLocal.getInn(), profileDto.getInn()))
                || (profileLocal.getSnils() != null && !Objects.equals(profileLocal.getSnils(), profileDto.getSnils())))
            throw new BadRequestException("Request method update(). Вы поменяли id профиля, либо ИНН или номер телефона уже разегистрированы");

        if (!Objects.equals(profileLocal.getPassport().getId(), profileDto.getPassport().getId())
                || !Objects.equals(profileLocal.getPassport().getRegistration().getId(), profileDto.getPassport().getRegistration().getId())
                || (
                profileLocal.getActualRegistration() != null
                        && profileDto.getActualRegistration() != null
                        && !Objects.equals(profileLocal.getActualRegistration().getId(), profileDto.getActualRegistration().getId())
                ))
            throw new BadRequestException("Request method update(). Вы поменяли id паспорта или адресов регистрации");

        log.info("Request method update(id={}). Запись успешно отредактирована", profileDto.getId());

        Profile profile = ProfileMapper.INSTANCE.profileDtoToProfile(profileDto);
        profile.setEntityType(profileLocal.getEntityType() == null ? null : profileLocal.getEntityType());
        profile.setOperationType(profileLocal.getOperationType() == null ? null : profileLocal.getOperationType());
        profile.setCreatedBy(profileLocal.getCreatedBy() == null ? null : profileLocal.getCreatedBy());
        profile.setModifiedBy(profileLocal.getModifiedBy() == null ? null : profileLocal.getModifiedBy());
        profile.setCreatedAt(profileLocal.getCreatedAt() == null ? null : profileLocal.getCreatedAt());
        profile.setModifiedAt(profileLocal.getModifiedAt() == null ? null : profileLocal.getModifiedAt());
        profile.setNewEntityJson(profileLocal.getNewEntityJson() == null ? null : profileLocal.getNewEntityJson());
        profile.setEntityJson(profileLocal.getEntityJson() == null ? null : profileLocal.getEntityJson());

        return ProfileMapper.INSTANCE.profileToProfileDto(
                profileRepository.save(profile));
    }

}
