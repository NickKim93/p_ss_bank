package com.bank.publicinfo.service;

import com.bank.publicinfo.dto.LicenseDto;

import java.util.List;

public interface LicenseService {

    LicenseDto getLicenseById(Long id);

    List<LicenseDto> getAllLicenses ();

    LicenseDto createLicense(LicenseDto licenseDto);

    LicenseDto updateLicense(Long id, LicenseDto licenseDto);

    void deleteLicenseById(Long id);
}
