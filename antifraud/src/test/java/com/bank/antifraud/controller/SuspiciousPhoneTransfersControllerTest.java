package com.bank.antifraud.controller;

import com.bank.antifraud.entity.PhoneEntity;
import com.bank.antifraud.service.SuspiciousTransferService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SuspiciousPhoneTransfersController.class)
@DisplayName("Контроллер - подозрительные транзакции (phone)")
class SuspiciousPhoneTransfersControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private SuspiciousTransferService<PhoneEntity> suspiciousPhoneTransfersService;

    @Test
    @DisplayName("GET /phone/transfers/all должен возвращать пустой лист и статус ок")
    void getAll_mustReturnEmptyList() throws Exception {
        // given
        List<PhoneEntity> result = new ArrayList<>();

        // when
        when(suspiciousPhoneTransfersService.findAll()).thenReturn(result);

        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/phone/transfers/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    @DisplayName("GET /phone/transfers/{id} возвращает сущность и статус 200, когда сущность существует")
    void getById_mustReturnAudit_whenAuditIsExists() throws Exception {
        // given
        PhoneEntity phoneEntity = new PhoneEntity();
        phoneEntity.setTransferId(1L);
        phoneEntity.setId(1L);
        phoneEntity.setIsSuspicious(true);

        // when
        when(suspiciousPhoneTransfersService.findById(1L)).thenReturn(phoneEntity);

        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/phone/transfers/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.transferId", Matchers.is(1)))
                .andExpect(jsonPath("$.isSuspicious", Matchers.is(true)));
    }

    @Test
    @DisplayName("GET /phone/transfers/{id} возвращает ошибку 404, когда сущность не найдена")
    void getById_mustReturn404_whenAuditNotFound() throws Exception {
        // given

        // when
        when(suspiciousPhoneTransfersService.findById(1L)).thenThrow(new EntityNotFoundException());

        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/phone/transfers/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("DELETE /phone/transfers/{id} возвращает статус 200 (ok) когда сущность существовала")
    void deleteAudit_mustBeOk_whenAuditExistAndSuccessfulDel() throws Exception {
        // given

        // when
        doAnswer(invocationOnMock -> null).when(suspiciousPhoneTransfersService).delete(1L);

        // then
        mockMvc.perform(MockMvcRequestBuilders.delete("/phone/transfers/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("DELETE /phone/transfers/{id} возвращает 404, если сущности с таким id не существовало")
    void deleteAudit_mustBeError404_whenAuditNotExist() throws Exception {
        // given

        // when
        doThrow(new EntityNotFoundException()).when(suspiciousPhoneTransfersService).delete(1L);

        // then
        mockMvc.perform(MockMvcRequestBuilders.delete("/phone/transfers/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST /phone/transfers возвращает новый объект и статус 200")
    void save_mustReturnNewObj_whenCreateIsOk() throws Exception {
        // given
        PhoneEntity phoneEntity = new PhoneEntity();
        phoneEntity.setId(1L);
        phoneEntity.setTransferId(1L);
        phoneEntity.setIsSuspicious(true);

        // when
        when(suspiciousPhoneTransfersService.save(any())).thenReturn(phoneEntity);

        // then
        String json = new ObjectMapper().writeValueAsString(phoneEntity);
        mockMvc.perform(MockMvcRequestBuilders.post("/phone/transfers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)));
    }

    @Test
    @DisplayName("POST /phone/transfers возвращает ошибку, если уникальное поле - не уникальное")
    void save_mustReturnErr_whenUniqueFieldIsAlreadyExist() throws Exception {
        // given

        // when
        when(suspiciousPhoneTransfersService.save(null)).thenThrow(new ConstraintViolationException(null, null, null));

        // then
        mockMvc.perform(MockMvcRequestBuilders.post("/phone/transfers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("PATCH /phone/transfers возвращает новый объект, когда успешно изменен старый")
    void update_mustReturnNewObject_whenIdExist() throws Exception {
        // given
        PhoneEntity phoneEntity = new PhoneEntity();
        phoneEntity.setTransferId(1L);
        phoneEntity.setIsSuspicious(true);

        // when
        when(suspiciousPhoneTransfersService.save(any())).thenReturn(phoneEntity);

        // then
        String json = new ObjectMapper().writeValueAsString(phoneEntity);
        mockMvc.perform(MockMvcRequestBuilders.post("/phone/transfers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transferId", Matchers.is(1)))
                .andExpect(jsonPath("$.isSuspicious", Matchers.is(true)));
    }
}