package com.bank.antifraud.controller;

import com.bank.antifraud.entity.AuditEntity;
import com.bank.antifraud.service.AuditService;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AuditController.class)
@DisplayName("Контроллер - аудит")
class AuditControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuditService auditServiceMock;

    @Test
    @DisplayName("Получение пустого списка")
    public void testFindAll() throws Exception {
        // given
        List<AuditEntity> auditEntities = new ArrayList<>();

        // when
        when(auditServiceMock.findAll()).thenReturn(auditEntities);

        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/audit/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}