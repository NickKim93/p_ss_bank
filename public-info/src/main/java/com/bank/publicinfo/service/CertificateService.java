package com.bank.publicinfo.service;

import com.bank.publicinfo.entity.Certificate;
import java.util.List;
import java.util.Optional;

public interface CertificateService {

    Optional<Certificate> getCertificateById(Long id);

    List<Certificate> getAllCertificates ();

    Certificate createLicense(Certificate certificate);

    Optional<Certificate> updateCertificate(Long id, Certificate certificate);

    boolean deleteCertificateById(Long id);
}
