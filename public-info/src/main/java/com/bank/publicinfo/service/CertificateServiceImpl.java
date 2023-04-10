package com.bank.publicinfo.service;

import com.bank.publicinfo.entity.Certificate;
import com.bank.publicinfo.repository.CertificateRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CertificateServiceImpl implements CertificateService {

    private final CertificateRepository certificateRepository;

    public CertificateServiceImpl(CertificateRepository certificateRepository) {
        this.certificateRepository = certificateRepository;
    }

    @Override
    public Optional<Certificate> getCertificateById(BigInteger id) {
        return certificateRepository.findById(id);
    }

    @Override
    public List<Certificate> getAllCertificates() {
        return certificateRepository.findAll();
    }

    @Override
    public Certificate createLicense(Certificate certificate) {
        return certificateRepository.save(certificate);
    }

    @Override
    public Optional<Certificate> updateCertificate(BigInteger id, Certificate certificate) {
        Optional<Certificate> existingCertificate = certificateRepository.findById(id);
        if (existingCertificate.isPresent()) {
            Certificate updatedCertificate = existingCertificate.get();
            updatedCertificate.setPhoto(certificate.getPhoto());
            updatedCertificate.setBankDetails(certificate.getBankDetails());
            return Optional.of(certificateRepository.save(updatedCertificate));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean deleteCertificateById(BigInteger id) {
        Optional<Certificate> certificate = certificateRepository.findById(id);
        if (certificate.isPresent()) {
            certificateRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
