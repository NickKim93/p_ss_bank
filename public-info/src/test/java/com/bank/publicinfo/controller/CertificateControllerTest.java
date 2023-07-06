package com.bank.publicinfo.controller;

import com.bank.publicinfo.dto.CertificateDto;
import com.bank.publicinfo.service.CertificateService;
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
@WebMvcTest(CertificateController.class)
class CertificateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CertificateService certificateService;

    private List<CertificateDto> certificates;

    @BeforeEach
    void setUp() {
        certificates = Arrays.asList(
                new CertificateDto(1L, "".getBytes(), 1L),
                new CertificateDto(2L, "".getBytes(), 1L)
        );
    }

    @Test
    void testGetAllCertificates() throws Exception {
        // given
        when(certificateService.getAllCertificates()).thenReturn(certificates);

        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/certificates"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .json("[{\"id\":1,\"photo\":\"\",\"bankDetailsId\":1}" +
                                ",{\"id\":2,\"photo\":\"\",\"bankDetailsId\":1}]"));
        // then
        verify(certificateService, times(1)).getAllCertificates();
    }

    @Test
    void testCreateCertificate() throws Exception {
        // given
        CertificateDto createdDto = new CertificateDto(1L, "".getBytes(), 1L);

        // when
        when(certificateService.createCertificate(any())).thenReturn(createdDto);

        // then
        mockMvc.perform(MockMvcRequestBuilders.post("/certificates")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"photo\":\"\",\"bankDetailsId\":1}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    void testUpdateCertificate() throws Exception {
        // given
        Long certificateId = 1L;
        CertificateDto updatedDto = new CertificateDto(certificateId, "".getBytes(), 1L);

        // when
        given(certificateService.updateCertificate(eq(certificateId), any(CertificateDto.class))).willReturn(updatedDto);

        // then
        mockMvc.perform(MockMvcRequestBuilders.put("/certificates/{id}", certificateId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"photo\":\"\",\"bankDetailsId\":1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(certificateId.intValue())));

        verify(certificateService).updateCertificate(eq(certificateId), any(CertificateDto.class));
    }

    @Test
    void testDeleteById() throws Exception {
        // given
        // when
        mockMvc.perform(MockMvcRequestBuilders.delete("/certificates/1"))
                .andExpect(status().isNoContent());
        // then
        verify(certificateService, times(1)).deleteCertificateById(1L);
    }

}