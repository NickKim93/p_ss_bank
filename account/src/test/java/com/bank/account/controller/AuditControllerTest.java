package com.bank.account.controller;

import com.bank.account.controller.AuditController;
import com.bank.account.entity.Audit;
import com.bank.account.exception.AuditNotFoundException;
import com.bank.account.service.AuditService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuditController.class)
public class AuditControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AuditService service;
    @Autowired
    private AuditController controller;


    @Test
    public void test() {
        assertThat(controller).isNotNull();
    }

    @Test
    public void shouldReturnAllAudits() throws Exception {
        when(service.getAllAudits()).thenReturn(getAudits());
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/audit/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void shouldReturnAuditById() throws Exception {
        when(service.getAudit(1L)).thenReturn(getAudits().get(0));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/audit/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)));
    }
    @Test
    public void shouldReturnExceptionOnFindAuditById() throws Exception {
        when(service.getAudit(2L)).thenThrow(new AuditNotFoundException());
        mockMvc.perform(MockMvcRequestBuilders
                .get("/audit/{id}", 2L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
    List<Audit> getAudits() {
        Audit firstAudit = new Audit(1L, "entity_type", "operation_type", "by_me", "by_me_too", Timestamp.valueOf(LocalDateTime.now()),
                Timestamp.valueOf(LocalDateTime.now()), "new_entity", "entity");
        Audit secondtAudit = new Audit(2L, "entity_type", "operation_type", "by_me", "by_me_too", Timestamp.valueOf(LocalDateTime.now()),
                Timestamp.valueOf(LocalDateTime.now()), "new_entity", "entity");
        return List.of(firstAudit, secondtAudit);
    }
}
