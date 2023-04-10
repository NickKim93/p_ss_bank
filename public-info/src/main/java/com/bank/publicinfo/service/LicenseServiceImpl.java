package com.bank.publicinfo.service;

import com.bank.publicinfo.entity.License;
import com.bank.publicinfo.repository.LicenseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LicenseServiceImpl implements LicenseService {

    private final LicenseRepository licenseRepository;

    public LicenseServiceImpl(LicenseRepository licenseRepository) {
        this.licenseRepository = licenseRepository;
    }

    @Override
    public Optional<License> getLicenseById(BigInteger id) {
        return licenseRepository.findById(id);
    }

    @Override
    public List<License> getAllLicenses() {
        return licenseRepository.findAll();
    }

    @Override
    public License createLicense(License license) {
        return licenseRepository.save(license);
    }

    @Override
    public Optional<License> updateLicense(BigInteger id, License license) {
        Optional<License> existingLicense = licenseRepository.findById(id);
        if (existingLicense.isPresent()) {
            License updatedLicense = existingLicense.get();
            updatedLicense.setPhoto(license.getPhoto());
            updatedLicense.setBankDetails(license.getBankDetails());
            return Optional.of(licenseRepository.save(updatedLicense));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean deleteLicenseById(BigInteger id) {
        Optional<License> license = licenseRepository.findById(id);
        if (license.isPresent()) {
            licenseRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
