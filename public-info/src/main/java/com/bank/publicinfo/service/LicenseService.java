package com.bank.publicinfo.service;

import com.bank.publicinfo.entity.License;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface LicenseService {

    Optional<License> getLicenseById(Long id);

    List<License> getAllLicenses ();

    License createLicense(License license);

    Optional<License> updateLicense(Long id, License license);

    public boolean deleteLicenseById(Long id);
}
