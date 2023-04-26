package com.bank.publicinfo.controller;

import com.bank.publicinfo.dto.BranchDto;
import com.bank.publicinfo.service.BranchService;
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
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
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
@WebMvcTest(BranchController.class)
class BranchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BranchService branchService;

    private List<BranchDto> branches;

    @BeforeEach
    public void setUp() {
        branches = Arrays.asList(
                new BranchDto(1L, "1234", BigInteger.valueOf(1), "Moscow"
                        , LocalDateTime.of(2023, 04, 25, 23, 00, 01)
                        , LocalDateTime.of(2023, 04, 25, 23, 00, 01)
                        , new HashSet<>()),
                new BranchDto(2L, "1234", BigInteger.valueOf(1), "Moscow",
                        LocalDateTime.of(2023, 04, 25, 23, 00, 01),
                        LocalDateTime.of(2023, 04, 25, 23, 00, 01),
                        new HashSet<>())
        );
    }

    @Test
    void testGetAllBranches() throws Exception {
        // given
        when(branchService.getAllBranches()).thenReturn(branches);

        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/branches"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .json("[{\"id\":1,\"address\":\"1234\",\"phoneNumber\":1,\"city\":\"Moscow\",\"startOfWork\":\""
                                + LocalDateTime.of(2023, 04, 25, 23, 00, 01)
                                + "\",\"endOfWork\":\"" + LocalDateTime.of(2023, 04, 25, 23, 00, 01)
                                + "\",\"atmList\":[]}" +
                                ",{\"id\":2,\"address\":\"1234\",\"phoneNumber\":1,\"city\":\"Moscow\",\"startOfWork\":\""
                                + LocalDateTime.of(2023, 04, 25, 23, 00, 01)
                                + "\",\"endOfWork\":\"" + LocalDateTime.of(2023, 04, 25, 23, 00, 01)
                                + "\",\"atmList\":[]}]"));
        // then
        verify(branchService, times(1)).getAllBranches();
    }

    @Test
    void testCreateBranch() throws Exception {
        // given
        BranchDto createdDto =   new BranchDto(1L, "1234", BigInteger.valueOf(1), "Moscow"
                , LocalDateTime.of(2023, 04, 25, 23, 00, 01)
                , LocalDateTime.of(2023, 04, 25, 23, 00, 01)
                , new HashSet<>());

        // when
        when(branchService.createBranch(any())).thenReturn(createdDto);

        // then
        mockMvc.perform(MockMvcRequestBuilders.post("/branches")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"address\":\"1234\",\"phoneNumber\":1,\"city\":\"Moscow\",\"startOfWork\":\""
                                + LocalDateTime.of(2023, 04, 25, 23, 00, 01)
                                + "\",\"endOfWork\":\"" + LocalDateTime.of(2023, 04, 25, 23, 00, 01)
                                + "\",\"atmList\":[]}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", Matchers.is(1)));
    }

    @Test
    void testUpdateBranch() throws Exception {
        // given
        Long branchId = 1L;
        BranchDto updatedDto = new BranchDto(1L, "1234", BigInteger.valueOf(1), "Moscow"
                , LocalDateTime.of(2023, 04, 25, 23, 00, 01)
                , LocalDateTime.of(2023, 04, 25, 23, 00, 01)
                , new HashSet<>());

        // when
        given(branchService.updateBranch(eq(branchId), any(BranchDto.class))).willReturn(updatedDto);

        // then
        mockMvc.perform(MockMvcRequestBuilders.put("/branches/{id}", branchId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"address\":\"1234\",\"phoneNumber\":1,\"city\":\"Moscow\",\"startOfWork\":\""
                                + LocalDateTime.of(2023, 04, 25, 23, 00, 01)
                                + "\",\"endOfWork\":\"" + LocalDateTime.of(2023, 04, 25, 23, 00, 01)
                                + "\",\"atmList\":[]}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(branchId.intValue())));

        verify(branchService).updateBranch(eq(branchId), any(BranchDto.class));
    }

    @Test
    void testDeleteById() throws Exception {
        // given
        // when
        mockMvc.perform(MockMvcRequestBuilders.delete("/branches/1"))
                .andExpect(status().isNoContent());
        // then
        verify(branchService, times(1)).deleteBranchById(1L);
    }
}
