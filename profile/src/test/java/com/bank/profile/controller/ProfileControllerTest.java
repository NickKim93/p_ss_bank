package com.bank.profile.controller;

import com.bank.profile.dto.*;
import com.bank.profile.service.ProfileService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.net.URI;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProfileControllerTest {

    ProfileDto profileDto1, profileDto2;

    @Mock
    ProfileService profileService;

    @InjectMocks
    ProfileController profileController;

    @BeforeEach
    void setUp() {
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
    @DisplayName("GET /api/profile - запрос списка профилей, список не пустой, ответ 200 OK")
    void handleFindAllProfile_ReturnsValidResponseEntity() {
        // given
        var profilesDto = List.of(profileDto1);
        doReturn(profilesDto).when(profileService).findAll();

        // when
        var responseEntity = profileController.findAll();

        // then
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, responseEntity.getHeaders().getContentType());
        assertEquals(profilesDto, responseEntity.getBody());
    }

    @Test
    @DisplayName("GET /api/profile - запрос списка профилей, список пустой, ответ 204 NO CONTENT")
    void handleFindAllProfile_ListNull_ReturnsValidResponseEntity() {
        // given
        var profilesDto = List.of();
        doReturn(profilesDto).when(profileService).findAll();

        // when
        var responseEntity = profileController.findAll();

        // then
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        assertNull(responseEntity.getHeaders().getContentType());
        assertNull(responseEntity.getBody());
    }

    @Test
    @DisplayName("GET /api/profile/{id} - запрос профиля по id, профиль существует, ответ 200 ОК")
    void handleFindOneProfile_ReturnsValidResponseEntity() {
        // given
        doReturn(profileDto1).when(profileService).findOne(1l);

        // when
        var responseEntity = profileController.findOne(1l);

        // then
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, responseEntity.getHeaders().getContentType());
        assertEquals(profileDto1, responseEntity.getBody());
    }

    @Test
    @DisplayName("GET /api/profile/n/{number} - запрос профиля по ИНН или номеру телефона, профиль существует, ответ 200 OK")
    void handleFindProfileByInnOrPhoneNumber_ReturnsValidResponseEntity() {
        // given
        doReturn(profileDto2).when(profileService).findByInnOrPhoneNumber(9999999999L);

        // when
        var responseEntity = profileController.findByInnOrPhoneNumber(9999999999L);

        // then
        verify(profileService).findByInnOrPhoneNumber(9999999999L);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, responseEntity.getHeaders().getContentType());
        assertEquals(profileDto2, responseEntity.getBody());
    }

    @Test
    @DisplayName("DELETE /api/profile/{id} - запрос на удаление профиля, , ответ 200 OK")
    void handleDeleteProfile_ReturnsValidResponseEntity() {
        // given

        // when
        var responseEntity = profileController.delete(1l);

        // then
        verify(profileService).delete(1l);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
        verifyNoMoreInteractions(profileService);
    }

    @Test
    @DisplayName("POST /api/profile/ - запрос на создание профиля, JSON валиден, ответ 201 CREATED")
    void handleCreateProfile_StructureEntityIsValid_ReturnsValidResponseEntity() {
        // given
        doReturn(profileDto1).when(profileService).create(profileDto1);

        // when
        var responseEntity = profileController.create(profileDto1);

        // then
        verify(profileService).create(profileDto1);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, responseEntity.getHeaders().getContentType());
        assertInstanceOf(profileDto1.getClass(), responseEntity.getBody());
        assertEquals(profileDto1, responseEntity.getBody());
        assertEquals(URI.create("/api/profile/" + profileDto1.getId()),
                responseEntity.getHeaders().getLocation());
        verifyNoMoreInteractions(profileService);
    }

    @Test
    @DisplayName("PATCH /api/profile/ - запрос на обновление профиля, JSON валиден, ответ 202 ACCEPTED")
    void handleUpdateProfile_StructureEntityIsValid_ReturnsValidResponseEntity() {
        // given
        doReturn(profileDto1).when(profileService).update(profileDto1);

        // when
        var responseEntity = profileController.update(profileDto1);

        // then
        verify(profileService).update(profileDto1);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, responseEntity.getHeaders().getContentType());
        assertInstanceOf(profileDto1.getClass(), responseEntity.getBody());
        assertEquals(profileDto1, responseEntity.getBody());
        verifyNoMoreInteractions(profileService);
    }
}