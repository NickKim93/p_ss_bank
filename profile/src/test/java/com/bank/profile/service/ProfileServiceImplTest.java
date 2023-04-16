package com.bank.profile.service;

import com.bank.profile.dto.ActualRegistrationDto;
import com.bank.profile.dto.PassportDto;
import com.bank.profile.dto.ProfileDto;
import com.bank.profile.dto.RegistrationDto;
import com.bank.profile.entity.ActualRegistration;
import com.bank.profile.entity.Passport;
import com.bank.profile.entity.Profile;
import com.bank.profile.entity.Registration;
import com.bank.profile.exception.BadRequestException;
import com.bank.profile.exception.EntityNotFoundException;
import com.bank.profile.repository.ProfileRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProfileServiceImplTest {

    Profile profile1, profile2;
    ProfileDto profileDto1, profileDto2;

    @Mock
    ProfileRepository profileRepository;

    @InjectMocks
    ProfileServiceImpl profileService;

    @BeforeEach
    void setUp() {
        profile1 = Profile.builder()
                .id(1l)
                .phoneNumber(9999999999L)
                .email("email@mail.com")
                .nameOnCard("First Last")
                .inn(999999999999L)
                .snils(99999999999L)
                .passport(Passport.builder()
                        .id(1l)
                        .series(9999)
                        .number(999999L)
                        .lastName("Last")
                        .firstName("First")
                        .middleName("Middle")
                        .gender("G")
                        .birthDate(LocalDate.ofEpochDay(2000-01-01))
                        .birthPlace("Москва")
                        .issuedBy("УВД гор.Москвы")
                        .dateOfIssue(LocalDate.ofEpochDay(2016-01-01))
                        .divisionCode(999999)
                        .expirationDate(null)
                        .registration(Registration.builder()
                                .id(1l)
                                .country("РФ")
                                .region(null)
                                .city(null)
                                .district(null)
                                .locality(null)
                                .street(null)
                                .houseNumber(null)
                                .houseBlock(null)
                                .flatNumber(null)
                                .index(999999L)
                                .build())
                        .build())
                .actualRegistration(ActualRegistration.builder()
                        .id(1l)
                        .country("РФ")
                        .region(null)
                        .city(null)
                        .district(null)
                        .locality(null)
                        .street(null)
                        .houseNumber(null)
                        .houseBlock(null)
                        .flatNumber(null)
                        .index(999999L)
                        .build())
                .accountDetailsSet(new HashSet<>())
                .build();

        profile2 = Profile.builder()
                .phoneNumber(9999998888L)
                .email("google@mail.com")
                .nameOnCard("FName LName")
                .inn(999999998888L)
                .snils(99999998888L)
                .passport(Passport.builder()
                        .series(9988)
                        .number(999888L)
                        .lastName("LName")
                        .firstName("FName")
                        .middleName("MName")
                        .gender("M")
                        .birthDate(LocalDate.ofEpochDay(1990-01-01))
                        .birthPlace("Екатеринбург")
                        .issuedBy("УВД гор.Екатеринбурга")
                        .dateOfIssue(LocalDate.ofEpochDay(2006-01-01))
                        .divisionCode(999888)
                        .expirationDate(null)
                        .registration(Registration.builder()
                                .country("Россия")
                                .region(null)
                                .city(null)
                                .district(null)
                                .locality(null)
                                .street(null)
                                .houseNumber(null)
                                .houseBlock(null)
                                .flatNumber(null)
                                .index(999888L)
                                .build())
                        .build())
                .actualRegistration(ActualRegistration.builder()
                        .country("Российская Федерация")
                        .region(null)
                        .city(null)
                        .district(null)
                        .locality(null)
                        .street(null)
                        .houseNumber(null)
                        .houseBlock(null)
                        .flatNumber(null)
                        .index(999888L)
                        .build())
                .accountDetailsSet(null)
                .build();

        profileDto1 = ProfileDto.builder()
                .id(1l)
                .phoneNumber(9999999999L)
                .email("email@mail.com")
                .nameOnCard("First Last")
                .inn(999999999999L)
                .snils(99999999999L)
                .passport(PassportDto.builder()
                        .id(1l)
                        .series(9999)
                        .number(999999L)
                        .lastName("Last")
                        .firstName("First")
                        .middleName("Middle")
                        .gender("G")
                        .birthDate(LocalDate.ofEpochDay(2000-01-01))
                        .birthPlace("Москва")
                        .issuedBy("УВД гор.Москвы")
                        .dateOfIssue(LocalDate.ofEpochDay(2016-01-01))
                        .divisionCode(999999)
                        .expirationDate(null)
                        .registration(RegistrationDto.builder()
                                .id(1l)
                                .country("РФ")
                                .region(null)
                                .city(null)
                                .district(null)
                                .locality(null)
                                .street(null)
                                .houseNumber(null)
                                .houseBlock(null)
                                .flatNumber(null)
                                .index(999999L)
                                .build())
                        .build())
                .actualRegistration(ActualRegistrationDto.builder()
                        .id(1l)
                        .country("РФ")
                        .region(null)
                        .city(null)
                        .district(null)
                        .locality(null)
                        .street(null)
                        .houseNumber(null)
                        .houseBlock(null)
                        .flatNumber(null)
                        .index(999999L)
                        .build())
                .accountDetailsSet(new HashSet<>())
                .build();

        profileDto2 = ProfileDto.builder()
                .phoneNumber(9999998888L)
                .email("google@mail.com")
                .nameOnCard("FName LName")
                .inn(999999998888L)
                .snils(99999998888L)
                .passport(PassportDto.builder()
                        .series(9988)
                        .number(999888L)
                        .lastName("LName")
                        .firstName("FName")
                        .middleName("MName")
                        .gender("M")
                        .birthDate(LocalDate.ofEpochDay(1990-01-01))
                        .birthPlace("Екатеринбург")
                        .issuedBy("УВД гор.Екатеринбурга")
                        .dateOfIssue(LocalDate.ofEpochDay(2006-01-01))
                        .divisionCode(999888)
                        .expirationDate(null)
                        .registration(RegistrationDto.builder()
                                .country("Россия")
                                .region(null)
                                .city(null)
                                .district(null)
                                .locality(null)
                                .street(null)
                                .houseNumber(null)
                                .houseBlock(null)
                                .flatNumber(null)
                                .index(999888L)
                                .build())
                        .build())
                .actualRegistration(ActualRegistrationDto.builder()
                        .country("Российская Федерация")
                        .region(null)
                        .city(null)
                        .district(null)
                        .locality(null)
                        .street(null)
                        .houseNumber(null)
                        .houseBlock(null)
                        .flatNumber(null)
                        .index(999888L)
                        .build())
                .accountDetailsSet(null)
                .build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Запрос списка профилей из базы, список не пустой, проверка количества и соответствия самих профилей")
    void handleFindAllProfile_SizeIsNotEmpty_ReturnsValidSizeListAndListEntity() {
        // given
        doReturn(List.of(profile1)).when(profileRepository).findAll();

        // when
        var profilesDto = profileService.findAll();

        // then
        assertNotNull(profilesDto);
        assertEquals(profilesDto.size(), 1);
        assertEquals(List.of(profileDto1), profilesDto);
    }

    @Test
    @DisplayName("Запрос списка профилей из базы, список пустой")
    void handleFindAllProfile_SizeIsEmpty_ReturnsZeroList() {
        // given
        doReturn(List.of()).when(profileRepository).findAll();

        // when
        var profilesDto = profileService.findAll();

        // then
        assertNotNull(profilesDto);
        assertEquals(profilesDto.size(), 0);
        assertEquals(List.of(), profilesDto);
    }

    @Test
    @DisplayName("Запрос профиля из базы по id, профиль существует")
    void handleFindProfileById_ReturnsValidEntity() {
        // given
        doReturn(Optional.of(profile1)).when(profileRepository).findById(1l);

        // when
        var profileDto = profileService.findOne(1l);

        // then
        assertNotNull(profileDto);
        assertInstanceOf(profileDto1.getClass(), profileDto);
        assertEquals(profileDto1, profileDto);
    }

    @Test
    @DisplayName("Запрос профиля из базы по id, id < 0")
    void handleFindProfileById_NegativeId_BadRequestException() {
        // given

        // when

        // then
        assertThrows(BadRequestException.class, () -> {
            profileService.findOne(-1l);
        });
    }

    @Test
    @DisplayName("Запрос профиля из базы по id, профиль не существует")
    void handleFindProfileById_MissingId_EntityNotFoundException() {
        // given
        doThrow(EntityNotFoundException.class).when(profileRepository).findById(1000000l);
        // when

        // then
        assertThrows(EntityNotFoundException.class, () -> {
            profileService.findOne(1000000l);
        });
    }

    @Test
    @DisplayName("Запрос профиля из базы по ИНН или номеру телефона, профиль существует")
    void handleFindProfileByIInnOrPhoneNumber_ReturnsValidEntity() {
        // given
        doReturn(profile1).when(profileRepository).findProfileByInnOrPhoneNumber(9999999999L, 9999999999L);

        // when
        var profileDto = profileService.findByInnOrPhoneNumber(9999999999L);

        // then
        assertNotNull(profileDto);
        assertInstanceOf(profileDto1.getClass(), profileDto);
        assertEquals(profileDto1, profileDto);
    }

    @Test
    @DisplayName("Запрос профиля из базы по ИНН или номеру телефона, значение < 0")
    void handleFindProfileByInnOrPhoneNumber_NegativeInnOrPhoneNumber_BadRequestException() {
        // given

        // when

        // then
        assertThrows(BadRequestException.class, () -> {
            profileService.findByInnOrPhoneNumber(-9999999999L);
        });
    }

    @Test
    @DisplayName("Запрос профиля из базы по ИНН или номеру телефона, профиль не существует")
    void handleFindProfileByInnOrPhoneNumber_MissingInnOrPhoneNumber_EntityNotFoundException() {
        // given
        doReturn(null).when(profileRepository).findProfileByInnOrPhoneNumber(8889999999L, 8889999999L);
        // when

        // then
        assertThrows(EntityNotFoundException.class, () -> {
            profileService.findByInnOrPhoneNumber(8889999999L);
        });
    }

    @Test
    @DisplayName("Создание нового профиля, ИНН и номер телефона уникальны, успешное создание")
    void handleCreateProfile_PropertyIsUnique_ReturnsValidEntity() {
        // given
        doReturn(profile1).when(profileRepository).save(profile1);
        doReturn(null).when(profileRepository).findProfileByInn(999999999999L);
        doReturn(null).when(profileRepository).findProfileByPhoneNumber(9999999999L);

        // when
        var profileDto = profileService.create(profileDto1);

        // then
        verify(profileRepository).save(profile1);
        assertNotNull(profileDto);
        assertInstanceOf(profileDto1.getClass(), profileDto);
        assertEquals(profileDto1, profileDto);
        verifyNoMoreInteractions(profileRepository);
    }

    @Test
    @DisplayName("Создание нового профиля, ИНН не уникален, BadRequestException")
    void handleCreateProfile_INNIsNotUnique_BadRequestException() {
        // given
        doReturn(profile1).when(profileRepository).findProfileByInn(999999999999L);

        // when

        // then
        verifyNoInteractions(profileRepository);
        assertThrows(BadRequestException.class, () -> {
            profileService.create(profileDto1);
        });
    }

    @Test
    @DisplayName("Создание нового профиля, номер телефона не уникален, BadRequestException")
    void handleCreateProfile_PhoneNumberIsNotUnique_BadRequestException() {
        // given
        doReturn(null).when(profileRepository).findProfileByInn(999999999999L);
        doReturn(profile1).when(profileRepository).findProfileByPhoneNumber(9999999999L);

        // when

        // then
        verifyNoInteractions(profileRepository);
        assertThrows(BadRequestException.class, () -> {
            profileService.create(profileDto1);
        });
    }

    @Test
    @DisplayName("Удаление профиля, профиль существует, успешное удаление")
    void handleDeleteProfile_ProfileIsExist_SuccessfulDeletion() {
        // given
        doReturn(true).when(profileRepository).existsById(1l);

        // when
        profileService.delete(1l);

        // then
        verify(profileRepository).deleteById(1l);
        verifyNoMoreInteractions(profileRepository);
    }

    @Test
    @DisplayName("Удаление профиля, профиль не существует, EntityNotFoundException")
    void handleDeleteProfile_ProfileIsNotExist_EntityNotFoundException() {
        // given
        doReturn(false).when(profileRepository).existsById(1l);

        // when

        // then
        verifyNoInteractions(profileRepository);
        assertThrows(EntityNotFoundException.class, () -> {
            profileService.delete(1l);
        });
    }

    @Test
    @DisplayName("Обновление профиля, профиль существует, данные для обновления валидны")
    void handleUpdateProfile_ProfileIsExist_RequestIsValid() {
        // given
        doReturn(Optional.of(profile1)).when(profileRepository).findById(1l);
        doReturn(profile1).when(profileRepository).save(profile1);

        // when
        var profileDto = profileService.update(profileDto1);

        // then
        verify(profileRepository).save(profile1);
        assertNotNull(profileDto);
        assertInstanceOf(profileDto1.getClass(), profileDto);
        assertEquals(profileDto1, profileDto);
        verifyNoMoreInteractions(profileRepository);
    }

    @Test
    @DisplayName("Обновление профиля, профиль отсутсвует, EntityNotFoundException")
    void handleUpdateProfile_ProfileIsNotExist_EntityNotFoundException() {
        // given
        doThrow(EntityNotFoundException.class).when(profileRepository).findById(1l);

        // when

        // then
        verifyNoInteractions(profileRepository);
        assertThrows(EntityNotFoundException.class, () -> {
            profileService.update(profileDto1);
        });
    }

    @Test
    @DisplayName("Обновление профиля, был изменен id, либо введен ИНН или номер телефона, существующий в базе, BadRequestException")
    void handleUpdateProfile_ReplaceProfileIdOrINNOrPhoneNumberIsExist_BadRequestException() {
        // given
        doReturn(Optional.of(profile2)).when(profileRepository).findById(2l);

        // when
        profileDto1.setId(2l);

        // then
        verifyNoInteractions(profileRepository);
        assertThrows(BadRequestException.class, () -> {
            profileService.update(profileDto1);
        });
    }

    @Test
    @DisplayName("Обновление профиля, был изменен id пасспорта или регистрации, BadRequestException")
    void handleUpdateProfile_ReplacePassportOrRegistrationId_BadRequestException() {
        // given
        doReturn(Optional.of(profile1)).when(profileRepository).findById(1l);

        // when
        profileDto1.getPassport().setId(2l);

        // then
        verifyNoInteractions(profileRepository);
        assertThrows(BadRequestException.class, () -> {
            profileService.update(profileDto1);
        });
    }
}