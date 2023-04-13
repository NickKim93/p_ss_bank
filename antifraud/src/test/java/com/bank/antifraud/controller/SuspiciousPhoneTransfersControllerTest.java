package com.bank.antifraud.controller;

import com.bank.antifraud.entity.SuspiciousPhoneTransfersEntity;
import com.bank.antifraud.service.SuspiciousPhoneTransfersService;
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
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SuspiciousPhoneTransfersController.class)
class SuspiciousPhoneTransfersControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private SuspiciousPhoneTransfersService suspiciousPhoneTransfersService;

    @Test
    void getAll_mustReturnEmptyList() throws Exception {
        List<SuspiciousPhoneTransfersEntity> result = new ArrayList<>();

        when(suspiciousPhoneTransfersService.findAll()).thenReturn(result);

        mockMvc.perform(MockMvcRequestBuilders.get("/phone/transfers/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void getById_mustReturnAutit_whenAuditIsExists() throws Exception {
        SuspiciousPhoneTransfersEntity suspiciousPhoneTransfersEntity = new SuspiciousPhoneTransfersEntity();
        suspiciousPhoneTransfersEntity.setPhoneTransferId(1L);
        suspiciousPhoneTransfersEntity.setId(1L);
        suspiciousPhoneTransfersEntity.setIsSuspicious(true);

        when(suspiciousPhoneTransfersService.findById(1L)).thenReturn(suspiciousPhoneTransfersEntity);

        mockMvc.perform(MockMvcRequestBuilders.get("/phone/transfers/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.phoneTransferId", Matchers.is(1)))
                .andExpect(jsonPath("$.isSuspicious", Matchers.is(true)));
    }

    @Test
    void getById_mustReturn404_whenAuditNotFound() throws Exception {
        when(suspiciousPhoneTransfersService.findById(1L)).thenThrow(new EntityNotFoundException());

        mockMvc.perform(MockMvcRequestBuilders.get("/phone/transfers/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteAudit_mustBeOk_whenAuditExistAndSuccessfulDel() throws Exception {
        doAnswer(invocationOnMock -> null).when(suspiciousPhoneTransfersService).delete(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/phone/transfers/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deleteAudit_mustBeError404_whenAuditNotExist() throws Exception {
        doThrow(new EntityNotFoundException()).when(suspiciousPhoneTransfersService).delete(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/phone/transfers/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void save_mustReturnNewObj_whenCreateIsOk() throws Exception {
        SuspiciousPhoneTransfersEntity suspiciousPhoneTransfersEntity = new SuspiciousPhoneTransfersEntity();
        suspiciousPhoneTransfersEntity.setId(1L);
        suspiciousPhoneTransfersEntity.setPhoneTransferId(1L);
        suspiciousPhoneTransfersEntity.setIsSuspicious(true);

        when(suspiciousPhoneTransfersService.save(any())).thenReturn(suspiciousPhoneTransfersEntity);

        String json = new ObjectMapper().writeValueAsString(suspiciousPhoneTransfersEntity);
        mockMvc.perform(MockMvcRequestBuilders.post("/phone/transfers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)));
    }

    @Test
    void save_mustReturnErr_whenUniqueFieldIsAlreadyExist() throws Exception {
        when(suspiciousPhoneTransfersService.save(null)).thenThrow(new ConstraintViolationException(null, null, null));

        mockMvc.perform(MockMvcRequestBuilders.post("/phone/transfers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void update_mustReturnNewObject_whenIdExist() throws Exception {
        SuspiciousPhoneTransfersEntity suspiciousPhoneTransfersEntity = new SuspiciousPhoneTransfersEntity();
        suspiciousPhoneTransfersEntity.setPhoneTransferId(1L);
        suspiciousPhoneTransfersEntity.setIsSuspicious(true);

        when(suspiciousPhoneTransfersService.save(any())).thenReturn(suspiciousPhoneTransfersEntity);

        String json = new ObjectMapper().writeValueAsString(suspiciousPhoneTransfersEntity);
        mockMvc.perform(MockMvcRequestBuilders.post("/phone/transfers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.phoneTransferId", Matchers.is(1)))
                .andExpect(jsonPath("$.isSuspicious", Matchers.is(true)));
    }
}