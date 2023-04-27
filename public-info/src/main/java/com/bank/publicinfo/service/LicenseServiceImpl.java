package com.bank.publicinfo.service;

import com.bank.publicinfo.auditlistener.Auditable;
import com.bank.publicinfo.dto.LicenseDto;
import com.bank.publicinfo.entity.License;
import com.bank.publicinfo.mapper.LicenseMapper;
import com.bank.publicinfo.repository.LicenseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
public class LicenseServiceImpl implements LicenseService {

    private final LicenseRepository licenseRepository;

    private final LicenseMapper licenseMapper;


    public LicenseServiceImpl(LicenseRepository licenseRepository, LicenseMapper licenseMapper) {
        this.licenseRepository = licenseRepository;
        this.licenseMapper = licenseMapper;
    }

    @Override
    public LicenseDto getLicenseById(Long id) {
        License license = licenseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("License not found"));
        return licenseMapper.licenseEntityToDto(license);
    }

    @Override
    public List<LicenseDto> getAllLicenses() {
        List<License> licenses = licenseRepository.findAll();
        return licenseMapper.licenseListEntityToDto(licenses);
    }

    @Override
    @Auditable(operationType = "create")
    public LicenseDto createLicense(LicenseDto licenseDto) {
        License license = licenseMapper.licenseDtoToEntity(licenseDto);
        license = licenseRepository.save(license);
        return licenseMapper.licenseEntityToDto(license);
    }

    @Override
    @Auditable(operationType = "update")
    public LicenseDto updateLicense(Long id, LicenseDto licenseDto) {
        License existingLicense = licenseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("License not found"));
        licenseMapper.update(licenseDto, existingLicense);
        existingLicense = licenseRepository.save(existingLicense);
        return licenseMapper.licenseEntityToDto(existingLicense);
    }

    @Override
    @Auditable(operationType = "delete")
    public void deleteLicenseById(Long id) {
        License license = licenseRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("License not found"));
        licenseRepository.delete(license);
    }
}
