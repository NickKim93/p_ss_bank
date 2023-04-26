package com.bank.publicinfo.controller;

import com.bank.publicinfo.dto.LicenseDto;
import com.bank.publicinfo.service.LicenseService;
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
@WebMvcTest(LicenseController.class)
class LicenseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LicenseService licenseService;

    private List<LicenseDto> licenses;

    @BeforeEach
    public void setUp() {
        licenses = Arrays.asList(
                new LicenseDto(1L, "".getBytes(), null),
                new LicenseDto(2L, "".getBytes(), null)
        );
    }

    @Test
    void testGetAllLicenses() throws Exception {
        // given
        when(licenseService.getAllLicenses()).thenReturn(licenses);

        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/licenses"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .json("[{\"id\":1,\"photo\":\"\",\"bankDetailsId\":null}" +
                                ",{\"id\":2,\"photo\":\"\",\"bankDetailsId\":null}]"));
        // then
        verify(licenseService, times(1)).getAllLicenses();
    }

    @Test
    void testCreateLicense() throws Exception {
        // given
        LicenseDto createdDto = new LicenseDto(1L, "".getBytes(), 1L);

        // when
        when(licenseService.createLicense(any())).thenReturn(createdDto);

        // then
        mockMvc.perform(MockMvcRequestBuilders.post("/licenses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"photo\":\"\",\"bankDetailsId\":1}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    void testUpdateLicense() throws Exception {
        // given
        Long certificateId = 1L;
        LicenseDto updatedDto = new LicenseDto(certificateId, "".getBytes(), 1L);

        // when
        given(licenseService.updateLicense(eq(certificateId), any(LicenseDto.class))).willReturn(updatedDto);

        // then
        mockMvc.perform(MockMvcRequestBuilders.put("/licenses/{id}", certificateId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"photo\":\"\",\"bankDetailsId\":1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(certificateId.intValue())));

        verify(licenseService).updateLicense(eq(certificateId), any(LicenseDto.class));
    }

    @Test
    void testDeleteById() throws Exception {
        // given
        // when
        mockMvc.perform(MockMvcRequestBuilders.delete("/licenses/1"))
                .andExpect(status().isNoContent());
        // then
        verify(licenseService, times(1)).deleteLicenseById(1L);
    }
}
