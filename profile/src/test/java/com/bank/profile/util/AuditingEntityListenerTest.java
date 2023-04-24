package com.bank.profile.util;

import com.bank.profile.entity.ActualRegistration;
import com.bank.profile.entity.Audit;
import com.bank.profile.entity.Passport;
import com.bank.profile.entity.Profile;
import com.bank.profile.entity.Registration;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class AuditingEntityListenerTest {

    Profile profile;

    @Mock
    ObjectMapper objectMapper;

    @InjectMocks
    AuditingEntityListener auditingEntityListener;

    @BeforeEach
    void setUp() {
        profile = Profile.builder()
                .id(1L)
                .phoneNumber(9999999999L)
                .email("email@mail.com")
                .nameOnCard("First Last")
                .inn(999999999999L)
                .snils(99999999999L)
                .passport(Passport.builder()
                        .id(1L)
                        .series(9999)
                        .number(999999L)
                        .lastName("Last")
                        .firstName("First")
                        .middleName("Middle")
                        .gender("G")
                        .birthDate(LocalDate.ofEpochDay(2000 - 01 - 01))
                        .birthPlace("Москва")
                        .issuedBy("УВД гор.Москвы")
                        .dateOfIssue(LocalDate.ofEpochDay(2016 - 01 - 01))
                        .divisionCode(999999)
                        .expirationDate(null)
                        .registration(Registration.builder()
                                .id(1L)
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
                        .id(1L)
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
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Метод PrePersist. Передаваемый объект расширяет класс Audit. Проверка вывода сообщения")
    void handlePrePersist_ObjectIsInstanceOfAudit_LogMessageDisplayed() throws JsonProcessingException {
        // given

        // when
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream defaultConsole = System.out;
        System.setOut(new PrintStream(output));
        auditingEntityListener.prePersist(profile);

        // then
        assertTrue(output.toString().contains("Запись успешно добавлена в таблицу аудита"));

        System.setOut(defaultConsole);
    }

    @Test
    @DisplayName("Метод PrePersist. Передаваемый объект не расширяет класс Audit. Никаких сообщений не выводится")
    void handlePrePersist_ObjectIsInstanceOfAudit_NoLogMessage() throws JsonProcessingException {
        // given

        // when
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream defaultConsole = System.out;
        System.setOut(new PrintStream(output));
        auditingEntityListener.prePersist(new Passport());

        // then
        assertFalse(output.toString().contains("Запись успешно добавлена в таблицу аудита"));

        System.setOut(defaultConsole);
    }

    @Test
    @DisplayName("Метод PreUpdate. Передаваемый объект расширяет класс Audit. Проверка вывода сообщения")
    void handlePreUpdate_ObjectIsInstanceOfAudit_LogMessageDisplayed() throws JsonProcessingException {
        // given

        // when
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream defaultConsole = System.out;
        System.setOut(new PrintStream(output));
        auditingEntityListener.preUpdate(profile);

        // then
        assertTrue(output.toString().contains("Запись успешно изменена в таблице аудита"));

        System.setOut(defaultConsole);
    }

    @Test
    @DisplayName("Метод PreUpdate. Передаваемый объект не расширяет класс Audit. Никаких сообщений не выводится")
    void handlePreUpdate_ObjectIsInstanceOfAudit_NoLogMessage() throws JsonProcessingException {
        // given

        // when
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream defaultConsole = System.out;
        System.setOut(new PrintStream(output));
        auditingEntityListener.preUpdate(new Passport());

        // then
        assertFalse(output.toString().contains("Запись успешно изменена в таблице аудита"));

        System.setOut(defaultConsole);
    }

    @Test
    @DisplayName("Неудачная попытка преобразования объекта Audit в JSON ObjectMapper`ом, JsonProcessingException")
    void handleCreateStructure_ObjectAuditIsNotValid_JsonProcessingException() {
        // given
        try {
            doThrow(JsonProcessingException.class).when(objectMapper).writeValueAsString(profile);
        } catch (JsonProcessingException exception) {
        }

        // when

        // then
        assertThrows(JsonProcessingException.class, () -> {
            auditingEntityListener.createStructure(profile, OperationType.CREATE);
        });
    }

    @Test
    @DisplayName("Удачная попытка преобразования объекта Audit в JSON ObjectMapper`ом, операция CREATE выполнена успешно")
    void handleCreateStructure_ObjectAuditIsValid_OperationCreateSuccessfully() throws JsonProcessingException {
        // given
        doReturn("test string").when(objectMapper).writeValueAsString(profile);

        // when
        Audit audit = auditingEntityListener.createStructure(profile, OperationType.CREATE);

        // then
        assertEquals(audit.getClass().getSimpleName(), audit.getEntityType());
        assertEquals(OperationType.CREATE.name(), audit.getOperationType());
        assertNotNull(audit.getCreatedBy());
        assertNull(audit.getModifiedBy());
        assertNotNull(audit.getNewEntityJson());
        assertNull(audit.getEntityJson());
    }

    @Test
    @DisplayName("Удачная попытка преобразования объекта Audit в JSON ObjectMapper`ом, операция UPDATE выполнена успешно")
    void handleCreateStructure_ObjectAuditIsValid_OperationUpdateSuccessfully() throws JsonProcessingException {
        // given
        doReturn("test string").when(objectMapper).writeValueAsString(profile);

        // when
        profile.setCreatedBy("SYSTEM");
        profile.setCreatedAt(OffsetDateTime.now());
        profile.setNewEntityJson("test");
        Audit audit = auditingEntityListener.createStructure(profile, OperationType.UPDATE);

        // then
        assertEquals(audit.getClass().getSimpleName(), audit.getEntityType());
        assertEquals(OperationType.UPDATE.name(), audit.getOperationType());
        assertNotNull(audit.getCreatedBy());
        assertNotNull(audit.getModifiedBy());
        assertNotNull(audit.getCreatedAt());
        assertNotNull(audit.getNewEntityJson());
        assertNotNull(audit.getEntityJson());
    }
}