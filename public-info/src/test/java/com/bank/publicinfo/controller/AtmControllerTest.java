package com.bank.publicinfo.controller;

import com.bank.publicinfo.dto.AtmDto;
import com.bank.publicinfo.service.AtmService;
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

import java.time.LocalDateTime;
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
@WebMvcTest(AtmController.class)
class AtmControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AtmService atmService;

    private List<AtmDto> atms;

    @BeforeEach
    void setUp() {
        atms = Arrays.asList(
                new AtmDto(1L, "Address 1", LocalDateTime.of(2023, 04, 25, 23, 00, 01)
                        , LocalDateTime.of(2023, 04, 25, 23, 00, 01), true, 1L),
                new AtmDto(2L, "Address 2", LocalDateTime.of(2023, 04, 25, 23, 00, 01)
                        , LocalDateTime.of(2023, 04, 25, 23, 00, 01), true, 1L)
        );
    }

    @Test
    void testGetAllAtms() throws Exception {
        // given
        when(atmService.getAtms()).thenReturn(atms);

        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/atms"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .json("[{\"id\":1,\"address\":\"Address 1\",\"startOfWork\":\""
                                + LocalDateTime.of(2023, 04, 25, 23, 00, 01) + "\",\"endOfWork\":\"" + LocalDateTime.of(2023, 04, 25, 23, 00, 01)
                                + "\",\"allHours\":true,\"branchId\":1}" +
                                ",{\"id\":2,\"address\":\"Address 2\",\"startOfWork\":\"" + LocalDateTime.of(2023, 04, 25, 23, 00, 01)
                                + "\",\"endOfWork\":\"" + LocalDateTime.of(2023, 04, 25, 23, 00, 01) + "\",\"allHours\":true,\"branchId\":1}]"));

        // then
        verify(atmService, times(1)).getAtms();
    }

    @Test
    void testCreateAtm() throws Exception {
        // given
        AtmDto atmDto = new AtmDto(null, "Address 1", LocalDateTime.of(2023, 04, 25, 23, 00, 01)
                , LocalDateTime.of(2023, 04, 25, 23, 00, 01), true, 1L);
        AtmDto createdDto = new AtmDto(1L, "Address 1", LocalDateTime.of(2023, 04, 25, 23, 00, 01)
                , LocalDateTime.of(2023, 04, 25, 23, 00, 01), true, 1L);

        // when
        given(atmService.createAtm(atmDto)).willReturn(createdDto);

        // then
        mockMvc.perform(MockMvcRequestBuilders.post("/atms")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"address\":\"Address 1\",\"startOfWork\":\""
                                + LocalDateTime.of(2023, 04, 25, 23, 00, 01)
                                + "\",\"endOfWork\":\""
                                + LocalDateTime.of(2023, 04, 25, 23, 00, 01)
                                + "\",\"allHours\":true,\"branchId\":1}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", Matchers.is(1)));
    }

    @Test
    void testUpdateAtm() throws Exception {
        // given
        Long atmId = 1L;
        AtmDto atmDto = new AtmDto(null, "New Address", LocalDateTime.of(2023, 4, 26, 0, 0, 01),
                LocalDateTime.of(2023, 4, 26, 0, 0, 01), false, 2L);
        AtmDto updatedDto = new AtmDto(atmId, "New Address2", LocalDateTime.of(2023, 4, 26, 0, 0, 01),
                LocalDateTime.of(2023, 4, 26, 0, 0, 01), false, 2L);

        // when
        given(atmService.updateAtm(eq(atmId), any(AtmDto.class))).willReturn(updatedDto);

        // then
        mockMvc.perform(MockMvcRequestBuilders.put("/atms/{id}", atmId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"address\":\"New Address2\",\"startOfWork\":\""
                                + LocalDateTime.of(2023, 4, 26, 0, 0, 01)
                                + "\",\"endOfWork\":\""
                                + LocalDateTime.of(2023, 4, 26, 0, 0, 01)
                                + "\",\"allHours\":false,\"branchId\":2}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(atmId.intValue())))
                .andExpect(jsonPath("$.address", is(updatedDto.address())))
                .andExpect(jsonPath("$.startOfWork", is(updatedDto.startOfWork().toString())))
                .andExpect(jsonPath("$.endOfWork", is(updatedDto.endOfWork().toString())))
                .andExpect(jsonPath("$.allHours", is(updatedDto.allHours())))
                .andExpect(jsonPath("$.branchId", is(updatedDto.branchId().intValue())));

        verify(atmService).updateAtm(eq(atmId), any(AtmDto.class));
    }



    @Test
    void testDeleteById() throws Exception {
        // given
        // when
        mockMvc.perform(MockMvcRequestBuilders.delete("/atms/1"))
                .andExpect(status().isNoContent());
        // then
        verify(atmService, times(1)).deleteAtmById(1L);
    }

}