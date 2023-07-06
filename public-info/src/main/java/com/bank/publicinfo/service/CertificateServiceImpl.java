package com.bank.publicinfo.service;

import com.bank.publicinfo.auditlistener.Auditable;
import com.bank.publicinfo.dto.CertificateDto;
import com.bank.publicinfo.entity.Certificate;
import com.bank.publicinfo.mapper.CertificateMapper;
import com.bank.publicinfo.repository.CertificateRepository;
import io.micrometer.core.annotation.Timed;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
public class CertificateServiceImpl implements CertificateService {

    private final CertificateRepository certificateRepository;

    private final CertificateMapper certificateMapper;

    public CertificateServiceImpl(CertificateRepository certificateRepository, CertificateMapper certificateMapper) {
        this.certificateRepository = certificateRepository;
        this.certificateMapper = certificateMapper;
    }

    @Override
    public CertificateDto getCertificateById(Long id) {
        Certificate certificate = certificateRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Certificate not found"));
        return certificateMapper.certificateEntityToDto(certificate);
    }

    @Override
    @Timed(value = "certificates_service.findAll")
    public List<CertificateDto> getAllCertificates() {
        List<Certificate> certificateList = certificateRepository.findAll();
        return certificateMapper.certificateListEntityToDto(certificateList);
    }

    @Override
    @Auditable(operationType = "create")
    @Timed(value = "certificates_service.create")
    public CertificateDto createCertificate(CertificateDto certificateDto) {
        Certificate certificate = certificateMapper.certificateDtoToEntity(certificateDto);
        certificate = certificateRepository.save(certificate);
        return certificateMapper.certificateEntityToDto(certificate);
    }

    @Override
    @Auditable(operationType = "update")
    @Timed(value = "certificates_service.update")
    public CertificateDto updateCertificate(Long id, CertificateDto certificateDto) {
        Certificate certificate = certificateRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Certificate not found"));
        certificateMapper.update(certificateDto, certificate);
        certificate = certificateRepository.save(certificate);
        return certificateMapper.certificateEntityToDto(certificate);
    }

    @Override
    @Auditable(operationType = "delete")
    @Timed(value = "certificates_service.delete")
    public void deleteCertificateById(Long id) {
        Certificate certificate = certificateRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Certificate not found"));
        certificateRepository.delete(certificate);
    }
}
