package com.bank.publicinfo.controller;

import com.bank.publicinfo.entity.Certificate;
import com.bank.publicinfo.service.CertificateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/certificates")
public class CertificateController {

    private final CertificateService certificateService;


    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Certificate> getLicense(@PathVariable Long id) {
        Optional<Certificate> certificate = certificateService.getCertificateById(id);
        return certificate.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Certificate>> getAllLicenses() {
        List<Certificate> certificates = certificateService.getAllCertificates();
        if (certificates.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(certificates);
        }
    }

    @PostMapping
    public ResponseEntity<Certificate> createLicense(@RequestBody Certificate certificate) {
        Certificate createdCertificate = certificateService.createLicense(certificate);
        return ResponseEntity.created(URI.create("/api/licenses" + createdCertificate.getId())).body(createdCertificate);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Certificate> updatedLicense(@PathVariable Long id, @RequestBody Certificate certificate) {
        Optional<Certificate> updatedCertificate = certificateService.updateCertificate(id, certificate);
        return updatedCertificate.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Certificate> deleteLicense(@PathVariable Long id) {
        boolean deleted = certificateService.deleteCertificateById(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
