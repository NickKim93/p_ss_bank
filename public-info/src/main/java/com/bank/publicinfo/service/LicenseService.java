package com.bank.publicinfo.service;

import com.bank.publicinfo.entity.License;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface LicenseService {

    public Optional<License> getLicenseById(BigInteger id);

    public List<License> getAllLicenses ();

    public License createLicense(License license);

    public Optional<License> updateLicense(BigInteger id, License license);

    public boolean deleteLicenseById(BigInteger id);
}
