package com.bank.publicinfo.service;

import com.bank.publicinfo.dto.LicenseDto;
import com.bank.publicinfo.entity.License;
import com.bank.publicinfo.mapper.LicenseMapper;
import com.bank.publicinfo.repository.BankDetailsRepository;
import com.bank.publicinfo.repository.LicenseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LicenseServiceImplTest {

    @Mock
    private LicenseRepository licenseRepository;

    @Mock
    private LicenseMapper licenseMapper;
    @Mock
    private BankDetailsRepository bankDetailsRepository;

    @InjectMocks
    private LicenseServiceImpl licenseService;

    private License license;
    private LicenseDto licenseDto;

    @BeforeEach
    public void setup() {
        license = new License(1L, "".getBytes(), null);
        licenseDto = new LicenseDto(1L, "".getBytes(), null);
    }

    @Test
    public void getLicenseByIdTest() {
        // given
        when(licenseRepository.findById(1L)).thenReturn(Optional.of(license));
        when(licenseMapper.licenseEntityToDto(any(License.class))).thenReturn(licenseDto);

        // when
        LicenseDto result = licenseService.getLicenseById(1L);

        // then
        assertEquals(result, licenseDto);
    }

    @Test
    void getLicenseByIdNotFoundExceptionTest() {
        // given
        Long id = 1L;
        when(licenseRepository.findById(id)).thenReturn(Optional.empty());

        // when and then
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class
                , () -> licenseService.getLicenseById(id));
        assertEquals("License not found", exception.getMessage());
        verify(licenseRepository, times(1)).findById(id);
        verifyNoMoreInteractions(licenseRepository, licenseMapper, bankDetailsRepository);
    }

    @Test
    public void getLicensesTest() {
        // given
        List<License> licenseList = new ArrayList<>();
        licenseList.add(license);
        when(licenseRepository.findAll()).thenReturn(licenseList);
        when(licenseMapper.licenseListEntityToDto(any(List.class))).thenReturn(List.of(licenseDto));

        // when
        List<LicenseDto> result = licenseService.getAllLicenses();

        // then
        assertEquals(result, List.of(licenseDto));
    }

    @Test
    public void createLicenseTest() {
        // given
        when(licenseMapper.licenseDtoToEntity(any(LicenseDto.class))).thenReturn(license);
        when(licenseRepository.save(any(License.class))).thenReturn(license);
        when(licenseMapper.licenseEntityToDto(any(License.class))).thenReturn(licenseDto);

        //when
        LicenseDto result = licenseService.createLicense(licenseDto);

        //then
        assertEquals(result, licenseDto);
    }

    @Test
    public void updateLicenseTest() {
        // given
        LicenseDto updatedLicenseDto = new LicenseDto(1L, "".getBytes(), 1L);
        when(licenseRepository.findById(1L)).thenReturn(Optional.of(license));
        when(licenseRepository.save(any(License.class))).thenReturn(license);
        when(licenseMapper.licenseEntityToDto(any(License.class))).thenReturn(updatedLicenseDto);

        // when
        LicenseDto result = licenseService.updateLicense(1L, updatedLicenseDto);

        // then
        assertEquals(result, updatedLicenseDto);
    }

    @Test
    void updateLicenseNotFoundExceptionTest() {
        // given
        Long id = 1L;
        LicenseDto updatedLicenseDto = new LicenseDto(1L, "".getBytes(), 1L);
        when(licenseRepository.findById(id)).thenReturn(Optional.empty());

        // when and then
        assertThrows(EntityNotFoundException.class, () -> licenseService.updateLicense(id, updatedLicenseDto));
    }

    @Test
    void deleteLicenseTest() {
        // given
        Long id = 1L;
        License license1 = new License();
        when(licenseRepository.findById(id)).thenReturn(Optional.of(license1));

        // when
        licenseService.deleteLicenseById(id);

        // then
        verify(licenseRepository, times(1)).delete(license1);
    }

    @Test
    void deleteLicenseNotFoundExceptionTest() {
        // given
        Long id = 1L;
        when(licenseRepository.findById(id)).thenReturn(Optional.empty());

        // when and then
        assertThrows(EntityNotFoundException.class, () -> licenseService.deleteLicenseById(id));
    }




}