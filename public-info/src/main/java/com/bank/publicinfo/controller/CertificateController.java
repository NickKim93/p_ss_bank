package com.bank.publicinfo.controller;

import com.bank.publicinfo.dto.CertificateDto;
import com.bank.publicinfo.service.CertificateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/certificates")
public class CertificateController {

    private final CertificateService certificateService;


    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @GetMapping
    public ResponseEntity<List<CertificateDto>> getAllLicenses() {
        List<CertificateDto> certificates = certificateService.getAllCertificates();
        return ResponseEntity.ok(certificates);
    }

    @PostMapping
    public ResponseEntity<CertificateDto> createLicense(@Valid  @RequestBody CertificateDto certificateDto) {
        CertificateDto createdCertificate = certificateService.createLicense(certificateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCertificate);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CertificateDto> updatedLicense(@PathVariable Long id, @Valid @RequestBody CertificateDto certificateDto) {
        CertificateDto updatedCertificate = certificateService.updateCertificate(id, certificateDto);
        return ResponseEntity.ok(updatedCertificate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLicense(@PathVariable Long id) {
        certificateService.deleteCertificateById(id);
        return ResponseEntity.noContent().build();
    }
}
