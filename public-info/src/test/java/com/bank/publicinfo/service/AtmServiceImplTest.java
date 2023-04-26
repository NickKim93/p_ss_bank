package com.bank.publicinfo.service;

import com.bank.publicinfo.dto.AtmDto;
import com.bank.publicinfo.entity.Atm;
import com.bank.publicinfo.entity.Branch;
import com.bank.publicinfo.mapper.AtmMapper;
import com.bank.publicinfo.repository.AtmRepository;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AtmServiceImplTest {

    @Mock
    private AtmRepository atmRepository;

    @Mock
    private AtmMapper atmMapper;

    @Mock
    private BranchRepository branchRepository;

    @InjectMocks
    private AtmServiceImpl atmService;

    private Atm atm;
    private AtmDto atmDto;
    private Branch branch;

    @BeforeEach
    public void setup() {
        atm = new Atm(1L, "12", LocalDateTime.now(), LocalDateTime.now(), true, null);
        atmDto = new AtmDto(1L, "123", LocalDateTime.now(), LocalDateTime.now(), true, null);
        branch = new Branch(1L, "1234", BigInteger.valueOf(5551234), "Moscow", LocalDateTime.now(), LocalDateTime.now(), new HashSet<>());
    }

    @Test
    public void getAtmByIdTest() {
        // given
        when(atmRepository.findById(1L)).thenReturn(Optional.of(atm));
        when(atmMapper.atmToDto(any(Atm.class))).thenReturn(atmDto);

        // when
        AtmDto result = atmService.getAtmById(1L);

        // then
        assertEquals(result, atmDto);
    }

    @Test
    void getAtmByIdNotFoundExceptionTest() {
        // given
        Long id = 1L;
        when(atmRepository.findById(id)).thenReturn(Optional.empty());

        // when and then
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> atmService.getAtmById(id));
        assertEquals("ATM not found", exception.getMessage());
        verify(atmRepository, times(1)).findById(id);
        verifyNoMoreInteractions(atmRepository, atmMapper, branchRepository);
    }

    @Test
    public void getAtmsTest() {
        // given
        List<Atm> atmList = new ArrayList<>();
        atmList.add(atm);
        when(atmRepository.findAll()).thenReturn(atmList);
        when(atmMapper.atmListToDtoList(any(List.class))).thenReturn(List.of(atmDto));

        // when
        List<AtmDto> result = atmService.getAtms();

        // then
        assertEquals(result, List.of(atmDto));
    }

    @Test
    public void createAtmTest() {
        // given
        when(atmMapper.atmToEntity(any(AtmDto.class))).thenReturn(atm);
        when(atmRepository.save(any(Atm.class))).thenReturn(atm);
        when(atmMapper.atmToDto(any(Atm.class))).thenReturn(atmDto);

        //when
        AtmDto result = atmService.createAtm(atmDto);

        //then
        assertEquals(result, atmDto);
    }

    @Test
    public void updateAtmTest() {
        // given
        AtmDto updatedAtmDto = new AtmDto(1L, "456 Main St", LocalDateTime.now(), LocalDateTime.now(), true, 1L);
        when(atmRepository.findById(1L)).thenReturn(Optional.of(atm));
        when(branchRepository.findById(1L)).thenReturn(Optional.of(branch));
        when(atmRepository.save(any(Atm.class))).thenReturn(atm);
        when(atmMapper.atmToDto(any(Atm.class))).thenReturn(updatedAtmDto);

        // when
        AtmDto result = atmService.updateAtm(1L, updatedAtmDto);

        // then
        assertEquals(result, updatedAtmDto);
    }

    @Test
    void updateAtmNotFoundExceptionTest() {
        // given
        Long id = 1L;
        AtmDto atmDto = new AtmDto(id, "New address", LocalDateTime.of(2023, 4, 24, 10, 0),
                LocalDateTime.of(2023, 4, 24, 18, 0), false, null);
        when(atmRepository.findById(id)).thenReturn(Optional.empty());

        // when and then
        assertThrows(EntityNotFoundException.class, () -> atmService.updateAtm(id, atmDto));
    }

    @Test
    void deleteAtmTest() {
        // given
        Long id = 1L;
        Atm atm = new Atm();
        when(atmRepository.findById(id)).thenReturn(Optional.of(atm));

        // when
        atmService.deleteAtmById(id);

        // then
        verify(atmRepository, times(1)).delete(atm);
    }

    @Test
    void deleteAtmNotFoundExceptionTest() {
        // given
        Long id = 1L;
        when(atmRepository.findById(id)).thenReturn(Optional.empty());

        // when and then
        assertThrows(EntityNotFoundException.class, () -> atmService.deleteAtmById(id));
    }
}
