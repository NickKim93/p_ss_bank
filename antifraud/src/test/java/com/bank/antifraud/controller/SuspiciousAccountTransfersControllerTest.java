package com.bank.antifraud.controller;

import com.bank.antifraud.entity.AccountEntity;
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
@WebMvcTest(SuspiciousAccountTransfersController.class)
@DisplayName("Контроллер - подозрительные транзакции (account)")
class SuspiciousAccountTransfersControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private SuspiciousTransferService<AccountEntity> suspiciousAccountTransfersService;

    @Test
    @DisplayName("GET /account/transfers/all возвращает пустой лист и статус 200")
    void getAll_mustReturnEmptyList() throws Exception {
        // given
        List<AccountEntity> result = new ArrayList<>();

        // when
        when(suspiciousAccountTransfersService.findAll()).thenReturn(result);

        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/account/transfers/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    @DisplayName("GET /account/transfers/{id} возвращает сущность и статус 200, когда сущность существует")
    void getById_mustReturnAudit_whenAuditIsExists() throws Exception {
        // given
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setTransferId(1L);
        accountEntity.setId(1L);
        accountEntity.setIsSuspicious(true);

        // when
        when(suspiciousAccountTransfersService.findById(1L)).thenReturn(accountEntity);

        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/account/transfers/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.transferId", Matchers.is(1)))
                .andExpect(jsonPath("$.isSuspicious", Matchers.is(true)));
    }

    @Test
    @DisplayName("GET /account/transfers/{id} возвращает ошибку 404, когда сущность не найдена")
    void getById_mustReturn404_whenAuditNotFound() throws Exception {
        // given

        // when
        when(suspiciousAccountTransfersService.findById(1L)).thenThrow(new EntityNotFoundException());

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/account/transfers/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("DELETE /account/transfers/{id} возвращает статус 200 (ok) когда сущность существовала")
    void deleteAudit_mustBeOk_whenAuditExistAndSuccessfulDel() throws Exception {
        //given

        // when
        doAnswer(invocationOnMock -> null).when(suspiciousAccountTransfersService).delete(1L);

        //then
        mockMvc.perform(MockMvcRequestBuilders.delete("/account/transfers/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("DELETE /account/transfers/{id} возвращает 404, если сущности с таким id не существовало")
    void deleteAudit_mustBeError404_whenAuditNotExist() throws Exception {
        // given

        // when
        doThrow(new EntityNotFoundException()).when(suspiciousAccountTransfersService).delete(1L);

        // then
        mockMvc.perform(MockMvcRequestBuilders.delete("/account/transfers/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST /account/transfers возвращает новый объект и статус 200")
    void save_mustReturnNewObj_whenCreateIsOk() throws Exception {
        // given
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(1L);
        accountEntity.setTransferId(1L);
        accountEntity.setIsSuspicious(true);

        // when
        when(suspiciousAccountTransfersService.save(any())).thenReturn(accountEntity);

        // then
        String json = new ObjectMapper().writeValueAsString(accountEntity);
        mockMvc.perform(MockMvcRequestBuilders.post("/account/transfers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)));
    }

    @Test
    @DisplayName("POST /account/transfers возвращает ошибку, если уникальное поле - не уникальное")
    void save_mustReturnErr_whenUniqueFieldIsAlreadyExist() throws Exception {
        // given

        // when
        when(suspiciousAccountTransfersService.save(null)).thenThrow(new ConstraintViolationException(null, null, null));

        // then
        mockMvc.perform(MockMvcRequestBuilders.post("/account/transfers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("PATCH /account/transfers возвращает новый объект, когда успешно изменен старый")
    void update_mustReturnNewObject_whenIdExist() throws Exception {
        //given
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setTransferId(1L);
        accountEntity.setIsSuspicious(true);

        // when
        when(suspiciousAccountTransfersService.update(any())).thenReturn(accountEntity);

        //then
        String json = new ObjectMapper().writeValueAsString(accountEntity);
        mockMvc.perform(MockMvcRequestBuilders.patch("/account/transfers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transferId", Matchers.is(1)))
                .andExpect(jsonPath("$.isSuspicious", Matchers.is(true)));
    }
}