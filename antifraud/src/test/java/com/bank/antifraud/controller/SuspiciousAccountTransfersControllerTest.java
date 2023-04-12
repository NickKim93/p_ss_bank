package com.bank.antifraud.controller;

import com.bank.antifraud.entity.SuspiciousAccountTransfersEntity;
import com.bank.antifraud.service.SuspiciousAccountTransfersService;
import com.bank.antifraud.util.AuditingEntityListener;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.hibernate.exception.ConstraintViolationException;
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
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SuspiciousAccountTransfersController.class)
class SuspiciousAccountTransfersControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private SuspiciousAccountTransfersService suspiciousAccountTransfersService;

    @Test
    void getAll_mustReturnEmptyList() throws Exception {
        List<SuspiciousAccountTransfersEntity> result = new ArrayList<>();

        when(suspiciousAccountTransfersService.findAll()).thenReturn(result);

        mockMvc.perform(MockMvcRequestBuilders.get("/account/transfers/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void getById_mustReturnAutit_whenAuditIsExists() throws Exception {
        SuspiciousAccountTransfersEntity suspiciousAccountTransfersEntity = new SuspiciousAccountTransfersEntity();
        suspiciousAccountTransfersEntity.setAccountTransferId(1L);
        suspiciousAccountTransfersEntity.setId(BigInteger.ONE);
        suspiciousAccountTransfersEntity.setIsSuspicious(true);

        when(suspiciousAccountTransfersService.findById(BigInteger.ONE)).thenReturn(suspiciousAccountTransfersEntity);

        mockMvc.perform(MockMvcRequestBuilders.get("/account/transfers/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.accountTransferId", Matchers.is(1)))
                .andExpect(jsonPath("$.isSuspicious", Matchers.is(true)));
    }

    @Test
    void getById_mustReturn404_whenAuditNotFound() throws Exception {
        when(suspiciousAccountTransfersService.findById(BigInteger.ONE)).thenThrow(new EntityNotFoundException());

        mockMvc.perform(MockMvcRequestBuilders.get("/account/transfers/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteAudit_mustBeOk_whenAuditExistAndSuccessfulDel() throws Exception {
        doAnswer(invocationOnMock -> null).when(suspiciousAccountTransfersService).delete(BigInteger.ONE);

        mockMvc.perform(MockMvcRequestBuilders.delete("/account/transfers/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deleteAudit_mustBeError404_whenAuditNotExist() throws Exception {
        doThrow(new EntityNotFoundException()).when(suspiciousAccountTransfersService).delete(BigInteger.ONE);

        mockMvc.perform(MockMvcRequestBuilders.delete("/account/transfers/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void save_mustReturnNewObj_whenCreateIsOk() throws Exception {
        SuspiciousAccountTransfersEntity suspiciousAccountTransfersEntity = new SuspiciousAccountTransfersEntity();
        suspiciousAccountTransfersEntity.setId(BigInteger.ONE);
        suspiciousAccountTransfersEntity.setAccountTransferId(1L);
        suspiciousAccountTransfersEntity.setIsSuspicious(true);

        when(suspiciousAccountTransfersService.save(any())).thenReturn(suspiciousAccountTransfersEntity);

        String json = new ObjectMapper().writeValueAsString(suspiciousAccountTransfersEntity);
        mockMvc.perform(MockMvcRequestBuilders.post("/account/transfers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)));
    }

    @Test
    void save_mustReturnErr_whenUniqueFieldIsAlreadyExist() throws Exception {
        when(suspiciousAccountTransfersService.save(null)).thenThrow(new ConstraintViolationException(null, null, null));

        mockMvc.perform(MockMvcRequestBuilders.post("/account/transfers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void update_mustReturnNewObject_whenIdExist() throws Exception {
        SuspiciousAccountTransfersEntity suspiciousAccountTransfersEntity = new SuspiciousAccountTransfersEntity();
        suspiciousAccountTransfersEntity.setAccountTransferId(1L);
        suspiciousAccountTransfersEntity.setIsSuspicious(true);

        when(suspiciousAccountTransfersService.save(any())).thenReturn(suspiciousAccountTransfersEntity);

        String json = new ObjectMapper().writeValueAsString(suspiciousAccountTransfersEntity);
        mockMvc.perform(MockMvcRequestBuilders.post("/account/transfers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountTransferId", Matchers.is(1)))
                .andExpect(jsonPath("$.isSuspicious", Matchers.is(true)));
    }
}