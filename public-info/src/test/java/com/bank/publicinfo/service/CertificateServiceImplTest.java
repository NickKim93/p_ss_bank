package com.bank.publicinfo.service;

import com.bank.publicinfo.dto.CertificateDto;
import com.bank.publicinfo.entity.Certificate;
import com.bank.publicinfo.mapper.CertificateMapper;
import com.bank.publicinfo.repository.BankDetailsRepository;
import com.bank.publicinfo.repository.CertificateRepository;
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
class CertificateServiceImplTest {

    @Mock
    private CertificateRepository certificateRepository;

    @Mock
    private BankDetailsRepository bankDetailsRepository;

    @Mock
    private CertificateMapper certificateMapper;
    @InjectMocks
    private CertificateServiceImpl certificateService;

    private Certificate certificate;

    private CertificateDto certificateDto;


    @BeforeEach
    public void setup() {
        certificate = new Certificate(1L, "".getBytes(), null);
        certificateDto = new CertificateDto(1L, "".getBytes(), null);
    }

    @Test
    public void getCertificateByIdTest() {
        // given
        when(certificateRepository.findById(1L)).thenReturn(Optional.of(certificate));
        when(certificateMapper.certificateEntityToDto(any(Certificate.class))).thenReturn(certificateDto);

        // when
        CertificateDto result = certificateService.getCertificateById(1L);

        // then
        assertEquals(result, certificateDto);
    }

    @Test
    void getCertificateByIdNotFoundExceptionTest() {
        // given
        Long id = 1L;
        when(certificateRepository.findById(id)).thenReturn(Optional.empty());

        // when and then
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class
                , () -> certificateService.getCertificateById(id));
        assertEquals("Certificate not found", exception.getMessage());
        verify(certificateRepository, times(1)).findById(id);
        verifyNoMoreInteractions(certificateRepository, certificateMapper, bankDetailsRepository);
    }

    @Test
    public void getCertificatesTest() {
        // given
        List<Certificate> certificateList = new ArrayList<>();
        certificateList.add(certificate);
        when(certificateRepository.findAll()).thenReturn(certificateList);
        when(certificateMapper.certificateListEntityToDto(any(List.class))).thenReturn(List.of(certificateDto));

        // when
        List<CertificateDto> result = certificateService.getAllCertificates();

        // then
        assertEquals(result, List.of(certificateDto));
    }

    @Test
    public void createAtmTest() {
        // given
        when(certificateMapper.certificateDtoToEntity(any(CertificateDto.class))).thenReturn(certificate);
        when(certificateRepository.save(any(Certificate.class))).thenReturn(certificate);
        when(certificateMapper.certificateEntityToDto(any(Certificate.class))).thenReturn(certificateDto);

        //when
        CertificateDto result = certificateService.createCertificate(certificateDto);

        //then
        assertEquals(result, certificateDto);
    }

    @Test
    public void updateCertificateTest() {
        // given
        CertificateDto updatedCertificatedDto = new CertificateDto(1L, "".getBytes(), 1L);
        when(certificateRepository.findById(1L)).thenReturn(Optional.of(certificate));
        when(certificateRepository.save(any(Certificate.class))).thenReturn(certificate);
        when(certificateMapper.certificateEntityToDto(any(Certificate.class))).thenReturn(updatedCertificatedDto);

        // when
        CertificateDto result = certificateService.updateCertificate(1L, updatedCertificatedDto);

        // then
        assertEquals(result, updatedCertificatedDto);
    }

    @Test
    void updateCertificateNotFoundExceptionTest() {
        // given
        Long id = 1L;
        CertificateDto updatedCertificatedDto = new CertificateDto(1L, "".getBytes(), 1L);
        when(certificateRepository.findById(id)).thenReturn(Optional.empty());

        // when and then
        assertThrows(EntityNotFoundException.class, () -> certificateService.updateCertificate(id, certificateDto));
    }

    @Test
    void deleteCertificateTest() {
        // given
        Long id = 1L;
        Certificate certificate1 = new Certificate();
        when(certificateRepository.findById(id)).thenReturn(Optional.of(certificate1));

        // when
        certificateService.deleteCertificateById(id);

        // then
        verify(certificateRepository, times(1)).delete(certificate1);
    }

    @Test
    void deleteCertificateNotFoundExceptionTest() {
        // given
        Long id = 1L;
        when(certificateRepository.findById(id)).thenReturn(Optional.empty());

        // when and then
        assertThrows(EntityNotFoundException.class, () -> certificateService.deleteCertificateById(id));
    }
}