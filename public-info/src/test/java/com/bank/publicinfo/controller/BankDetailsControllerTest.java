package com.bank.publicinfo.controller;

import com.bank.publicinfo.dto.BankDetailsDto;
import com.bank.publicinfo.service.BankDetailsService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.MediaType;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(BankDetailsController.class)
class BankDetailsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BankDetailsService bankDetailsService;

    private List<BankDetailsDto> bankDetailsList;

    @BeforeEach
    public void setUp() {
        bankDetailsList = Arrays.asList(
                new BankDetailsDto(1L,
                        BigInteger.valueOf(123),
                        BigInteger.valueOf(123),
                        BigInteger.valueOf(123),
                        123,
                        "Moscow", "LLC", "LLC", null, null),
                new BankDetailsDto(2L,
                        BigInteger.valueOf(123),
                        BigInteger.valueOf(123),
                        BigInteger.valueOf(123),
                        123,
                        "Moscow", "LLC", "LLC", null, null));
    }

    @Test
    void testGetAllBankDetails() throws Exception {
        // given
        when(bankDetailsService.getAllBankDetails()).thenReturn(bankDetailsList);

        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/bank_details"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .json("[{\"id\":1,\"bik\":123,\"inn\":123,\"kpp\":123,\"corAccount\":123" +
                                ",\"city\":\"Moscow\",\"jointStockCompany\":\"LLC\",\"name\":\"LLC\"" +
                                ",\"certificates\":null,\"licenses\":null}" +
                                ",{\"id\":2,\"bik\":123,\"inn\":123,\"kpp\":123,\"corAccount\":123" +
                                ",\"city\":\"Moscow\",\"jointStockCompany\":\"LLC\",\"name\":\"LLC\"" +
                                ",\"certificates\":null,\"licenses\":null}]"));
        // then
        verify(bankDetailsService, times(1)).getAllBankDetails();
    }

    @Test
    void testCreateBankDetails() throws Exception {
        // given
        BankDetailsDto createdDto =  new BankDetailsDto(1L,
                BigInteger.valueOf(123),
                BigInteger.valueOf(123),
                BigInteger.valueOf(123),
                123,
                "Moscow", "LLC", "LLC", null, null);

        // when
        when(bankDetailsService.createBankDetails(any())).thenReturn(createdDto);

        // then
        mockMvc.perform(MockMvcRequestBuilders.post("/bank_details")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"bik\":123,\"inn\":123,\"kpp\":123,\"corAccount\":123" +
                                ",\"city\":\"Moscow\",\"jointStockCompany\":\"LLC\",\"name\":\"LLC\"" +
                                ",\"certificates\":null,\"licenses\":null}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", Matchers.is(1)));
    }

    @Test
    void testUpdateBankDetails() throws Exception {
        // given
        Long bankDetailsId = 1L;
        BankDetailsDto updatedDto = new BankDetailsDto(1L,
                BigInteger.valueOf(123),
                BigInteger.valueOf(123),
                BigInteger.valueOf(123),
                123,
                "Moscow", "LLC", "LLC", null, null);

        // when
        given(bankDetailsService.updateBankDetails(eq(bankDetailsId), any(BankDetailsDto.class))).willReturn(updatedDto);

        // then
        mockMvc.perform(MockMvcRequestBuilders.put("/bank_details/{id}", bankDetailsId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"bik\":123,\"inn\":123,\"kpp\":123,\"corAccount\":123" +
                                ",\"city\":\"Moscow\",\"jointStockCompany\":\"LLC\",\"name\":\"LLC\"" +
                                ",\"certificates\":null,\"licenses\":null}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(bankDetailsId.intValue())));

        verify(bankDetailsService).updateBankDetails(eq(bankDetailsId), any(BankDetailsDto.class));
    }

}