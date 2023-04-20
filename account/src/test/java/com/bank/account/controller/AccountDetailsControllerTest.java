package com.bank.account.controller;

import com.bank.account.controller.AccountDetailsController;
import com.bank.account.dto.AccountDetailsDTO;
import com.bank.account.exception.AccountDetailsNotFoundException;
import com.bank.account.service.AccountDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigInteger;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AccountDetailsController.class)
public class AccountDetailsControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AccountDetailsService service;

    @Autowired
    private AccountDetailsController controller;

    @Test
    public void test() {
        assertThat(controller).isNotNull();
    }

    AccountDetailsDTO detailsDTO = new AccountDetailsDTO(1L, 1L, 1L, 1L, BigInteger.valueOf(220L), false, 1L);


    ResultMatcher[] matchers = {
            jsonPath("$.passportId", Matchers.is(1)),
            jsonPath("$.accountNumber", Matchers.is(1)),
            jsonPath("$.bankDetailsId", Matchers.is(1)),
            jsonPath("$.money", Matchers.is(220)),
            jsonPath("$.negativeBalance", Matchers.is(false)),
            jsonPath("$.profileId", Matchers.is(1)),
    };

    @Test
    public void shouldSaveAccountDetails() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/details/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(detailsDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnExceptionOnSave() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/details")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(new AccountDetailsDTO())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnAccountDetailsWithId() throws Exception {
        when(service.getAccountDetails(1L)).thenReturn(detailsDTO);
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/details/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpectAll(matchers);
    }

    @Test
    public void shouldReturnExceptionOnFindById() throws Exception {
        when(service.getAccountDetails(2L)).thenThrow(new AccountDetailsNotFoundException());
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/details/{id}", 2L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


    @Test
    public void shouldReturnAllAccountDetails() throws Exception {
        when(service.getAllAccountDetails()).thenReturn(getListDetailsDto());
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/details/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void shouldDeleteAccountDetailsById() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/details/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    public void shouldUpdateAccountDetails() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .patch("/details")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(detailsDTO)))
                .andExpect(status().isOk());
    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    List<AccountDetailsDTO> getListDetailsDto() {
        AccountDetailsDTO firstDto = new AccountDetailsDTO(1L, 1L, 1L, 1L, BigInteger.valueOf(220L), false, 1L);
        AccountDetailsDTO secondDto = new AccountDetailsDTO(2L, 2L, 2L, 2L, BigInteger.valueOf(220L), false, 2L);
        return List.of(firstDto, secondDto);
    }
}

