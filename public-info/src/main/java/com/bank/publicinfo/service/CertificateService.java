package com.bank.publicinfo.service;

import com.bank.publicinfo.entity.Certificate;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface CertificateService {

    public Optional<Certificate> getCertificateById(BigInteger id);

    public List<Certificate> getAllCertificates ();

    public Certificate createLicense(Certificate certificate);

    public Optional<Certificate> updateCertificate(BigInteger id, Certificate certificate);

    public boolean deleteCertificateById(BigInteger id);
}
