package com.bank.antifraud.controller;

import com.bank.antifraud.dto.AuditDto;
import com.bank.antifraud.service.AuditService;
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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AuditController.class)
class AuditControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AuditService auditService;

    @Test
    void getAll_mustReturnEmptyList() throws Exception {
        List<AuditDto> result = new ArrayList<>();

        when(auditService.findAll()).thenReturn(result);

        mockMvc.perform(MockMvcRequestBuilders.get("/audit/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void getById_mustReturnAutit_whenAuditIsExists() throws Exception {
        AuditDto auditDto = new AuditDto(
                BigInteger.ONE,
                "Entity",
                "Oper",
                "Makar",
                null,
                Timestamp.valueOf("2023-01-01 12:00:00"),
                null,
                null,
                "json"
        );

        when(auditService.findById(BigInteger.ONE)).thenReturn(auditDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/audit/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.entityType", Matchers.is("Entity")))
                .andExpect(jsonPath("$.entityJson", Matchers.is("json")));
    }

    @Test
    void getById_mustReturn404_whenAuditNotFound() throws Exception {
        when(auditService.findById(BigInteger.ONE)).thenThrow(new EntityNotFoundException());

        mockMvc.perform(MockMvcRequestBuilders.get("/audit/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteAudit_mustBeOk_whenAuditExistAndSuccessfulDel() throws Exception {
        doAnswer(invocationOnMock -> null).when(auditService).delete(BigInteger.ONE);

        mockMvc.perform(MockMvcRequestBuilders.delete("/audit/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deleteAudit_mustBeError404_whenAuditNotExist() throws Exception {
        doThrow(new EntityNotFoundException()).when(auditService).delete(BigInteger.ONE);

        mockMvc.perform(MockMvcRequestBuilders.delete("/audit/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void save_mustReturnNewObj_whenCreateIsOk() throws Exception {
        AuditDto auditDto = new AuditDto(
                null,
                "Entity",
                "Oper",
                "Makar",
                null,
                Timestamp.valueOf("2023-01-01 12:00:00"),
                null,
                null,
                "json"
        );
        AuditDto result = new AuditDto(
                BigInteger.ONE,
                "Entity",
                "Oper",
                "Makar",
                null,
                Timestamp.valueOf("2023-01-01 12:00:00"),
                null,
                null,
                "json"
        );

        when(auditService.save(auditDto)).thenReturn(result);

        String json = new ObjectMapper().writeValueAsString(auditDto);
        mockMvc.perform(MockMvcRequestBuilders.post("/audit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)));
    }

    @Test
    void save_mustReturnErr_whenUniqueFieldIsAlreadyExist() throws Exception {
        when(auditService.save(null)).thenThrow(new ConstraintViolationException(null, null, null));

        mockMvc.perform(MockMvcRequestBuilders.post("/audit")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void update_mustReturnNewObject_whenIdExist() throws Exception {
        AuditDto auditDto = new AuditDto(
                BigInteger.ONE,
                "Entity",
                "Oper",
                "Makar",
                null,
                Timestamp.valueOf("2023-01-01 12:00:00"),
                null,
                null,
                "json"
        );

        when(auditService.save(auditDto)).thenReturn(auditDto);

        String json = new ObjectMapper().writeValueAsString(auditDto);
        mockMvc.perform(MockMvcRequestBuilders.post("/audit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.entityType", Matchers.is("Entity")))
                .andExpect(jsonPath("$.entityJson", Matchers.is("json")));
    }
}