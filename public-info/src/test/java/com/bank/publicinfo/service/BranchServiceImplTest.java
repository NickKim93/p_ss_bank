package com.bank.publicinfo.service;

import com.bank.publicinfo.dto.BranchDto;
import com.bank.publicinfo.entity.Branch;
import com.bank.publicinfo.mapper.BranchMapper;
import com.bank.publicinfo.repository.BranchRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BranchServiceImplTest {

    @Mock
    private BranchMapper branchMapper;

    @Mock
    private BranchRepository branchRepository;

    @InjectMocks
    private BranchServiceImpl branchService;
    private Branch branch;

    private BranchDto branchDto;


    @BeforeEach
    public void setup() {
        branch = new Branch(1L, "1234", BigInteger.valueOf(5551234), "Moscow", LocalDateTime.now(), LocalDateTime.now(), new HashSet<>());
        branchDto = new BranchDto(1L, "1234", BigInteger.valueOf(5551234), "Moscow", LocalDateTime.now(), LocalDateTime.now(), new HashSet<>());
    }

    @Test
    void getBranchByIdTest() {
        // given
        when(branchRepository.findById(1L)).thenReturn(Optional.of(branch));
        when(branchMapper.branchToDto(any(Branch.class))).thenReturn(branchDto);

        // when
        BranchDto result = branchService.getBranchById(1L);

        // then
        assertEquals(result, branchDto);
    }

    @Test
    void getBranchByIdNotFoundExceptionTest() {
        // given
        Long id = 1L;
        when(branchRepository.findById(id)).thenReturn(Optional.empty());
        // when and then
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class
                , () -> branchService.getBranchById(id));
        assertEquals("Branch not found", exception.getMessage());
        verify(branchRepository, times(1)).findById(id);
        verifyNoMoreInteractions(branchRepository, branchMapper);
    }

    @Test
    void getAllBranches() {
        // given
        List<Branch> branchList = new ArrayList<>();
        branchList.add(branch);
        when(branchRepository.findAll()).thenReturn(branchList);
        when(branchMapper.branchListToDto(any(List.class))).thenReturn(List.of(branchDto));
        // when
        List<BranchDto> result = branchService.getAllBranches();
        // then
        assertEquals(result, List.of(branchDto));
    }

    @Test
    void createBranchTest() {
        // given
        when(branchMapper.branchToEntity(any(BranchDto.class))).thenReturn(branch);
        when(branchRepository.save(any(Branch.class))).thenReturn(branch);
        when(branchMapper.branchToDto(any(Branch.class))).thenReturn(branchDto);
        // when
        BranchDto result = branchService.createBranch(branchDto);
        // then
        assertEquals(result, branchDto);
    }

    @Test
    void updateBranchTest() {
        // given
        BranchDto updatedBranchDto = new BranchDto(1L
                , "1234", BigInteger.valueOf(5551234)
                , "Moscow", LocalDateTime.now(), LocalDateTime.now(), new HashSet<>());
        when(branchRepository.findById(1L)).thenReturn(Optional.of(branch));
        when(branchRepository.save(any(Branch.class))).thenReturn(branch);
        when(branchMapper.branchToDto(any(Branch.class))).thenReturn(updatedBranchDto);
        // when

        BranchDto result = branchService.updateBranch(1L, updatedBranchDto);

        // then
        assertEquals(result, updatedBranchDto);

    }

    @Test
    void updateBankDetailsNotFoundExceptionTest() {
        // given
        Long id = 1L;
        BranchDto branchDto1 = new BranchDto(1L
                , "1234", BigInteger.valueOf(5551234)
                , "Moscow", LocalDateTime.now(), LocalDateTime.now(), new HashSet<>());
        when(branchRepository.findById(id)).thenReturn(Optional.empty());

        // when and then
        assertThrows(EntityNotFoundException.class, () -> branchService.updateBranch(id, branchDto1));
    }

    @Test
    void deleteBranchByIdTest() {
        // given
        Long id = 1L;
        Branch branch1 = new Branch();
        when(branchRepository.findById(id)).thenReturn(Optional.of(branch1));
        // when
        branchService.deleteBranchById(id);
        // then
        verify(branchRepository, times(1)).delete(branch1);
    }

    @Test
    void deleteAtmNotFoundExceptionTest() {
        // given
        Long id = 1L;
        when(branchRepository.findById(id)).thenReturn(Optional.empty());

        // when and then
        assertThrows(EntityNotFoundException.class, () -> branchService.deleteBranchById(id));
    }
}