package com.bank.antifraud.controller;

import com.bank.antifraud.entity.SuspiciousAccountTransfersEntity;
import com.bank.antifraud.entity.SuspiciousCardTransferEntity;
import com.bank.antifraud.service.SuspiciousCardTransferService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SuspiciousCardTransferController.class)
class SuspiciousCardTransferControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private SuspiciousCardTransferService suspiciousCardTransferService;

    @Test
    void getAll_mustReturnEmptyList() throws Exception {
        List<SuspiciousCardTransferEntity> result = new ArrayList<>();

        when(suspiciousCardTransferService.findAll()).thenReturn(result);

        mockMvc.perform(MockMvcRequestBuilders.get("/card/transfer/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void getById_mustReturnAutit_whenAuditIsExists() throws Exception {
        SuspiciousCardTransferEntity suspiciousAccountTransfersEntity = new SuspiciousCardTransferEntity();
        suspiciousAccountTransfersEntity.setCardTransferId(1L);
        suspiciousAccountTransfersEntity.setId(BigInteger.ONE);
        suspiciousAccountTransfersEntity.setIsSuspicious(true);

        when(suspiciousCardTransferService.findById(BigInteger.ONE)).thenReturn(suspiciousAccountTransfersEntity);

        mockMvc.perform(MockMvcRequestBuilders.get("/card/transfer/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.cardTransferId", Matchers.is(1)))
                .andExpect(jsonPath("$.isSuspicious", Matchers.is(true)));
    }

    @Test
    void getById_mustReturn404_whenAuditNotFound() throws Exception {
        when(suspiciousCardTransferService.findById(BigInteger.ONE)).thenThrow(new EntityNotFoundException());

        mockMvc.perform(MockMvcRequestBuilders.get("/card/transfer/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteAudit_mustBeOk_whenAuditExistAndSuccessfulDel() throws Exception {
        doAnswer(invocationOnMock -> null).when(suspiciousCardTransferService).delete(BigInteger.ONE);

        mockMvc.perform(MockMvcRequestBuilders.delete("/card/transfer/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deleteAudit_mustBeError404_whenAuditNotExist() throws Exception {
        doThrow(new EntityNotFoundException()).when(suspiciousCardTransferService).delete(BigInteger.ONE);

        mockMvc.perform(MockMvcRequestBuilders.delete("/card/transfer/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void save_mustReturnNewObj_whenCreateIsOk() throws Exception {
        SuspiciousCardTransferEntity suspiciousAccountTransfersEntity = new SuspiciousCardTransferEntity();
        suspiciousAccountTransfersEntity.setId(BigInteger.ONE);
        suspiciousAccountTransfersEntity.setCardTransferId(1L);
        suspiciousAccountTransfersEntity.setIsSuspicious(true);

        when(suspiciousCardTransferService.save(any())).thenReturn(suspiciousAccountTransfersEntity);

        String json = new ObjectMapper().writeValueAsString(suspiciousAccountTransfersEntity);
        mockMvc.perform(MockMvcRequestBuilders.post("/card/transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)));
    }

    @Test
    void save_mustReturnErr_whenUniqueFieldIsAlreadyExist() throws Exception {
        when(suspiciousCardTransferService.save(null)).thenThrow(new ConstraintViolationException(null, null, null));

        mockMvc.perform(MockMvcRequestBuilders.post("/card/transfer")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void update_mustReturnNewObject_whenIdExist() throws Exception {
        SuspiciousCardTransferEntity suspiciousAccountTransfersEntity = new SuspiciousCardTransferEntity();
        suspiciousAccountTransfersEntity.setCardTransferId(1L);
        suspiciousAccountTransfersEntity.setIsSuspicious(true);

        when(suspiciousCardTransferService.save(any())).thenReturn(suspiciousAccountTransfersEntity);

        String json = new ObjectMapper().writeValueAsString(suspiciousAccountTransfersEntity);
        mockMvc.perform(MockMvcRequestBuilders.post("/card/transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cardTransferId", Matchers.is(1)))
                .andExpect(jsonPath("$.isSuspicious", Matchers.is(true)));
    }
}