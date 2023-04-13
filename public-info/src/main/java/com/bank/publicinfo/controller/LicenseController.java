package com.bank.publicinfo.controller;

import com.bank.publicinfo.dto.LicenseDto;
import com.bank.publicinfo.service.LicenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/licenses")
public class LicenseController {

    private final LicenseService licenseService;

    public LicenseController(LicenseService licenseService) {
        this.licenseService = licenseService;
    }

    @GetMapping
    public ResponseEntity<List<LicenseDto>> getAllLicenses() {
        List<LicenseDto> licenses = licenseService.getAllLicenses();
        return ResponseEntity.ok(licenses);
    }

    @PostMapping
    public ResponseEntity<LicenseDto> createLicense(@RequestBody LicenseDto licenseDto) {
        LicenseDto createdLicense = licenseService.createLicense(licenseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLicense);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LicenseDto> updatedLicense(@PathVariable Long id, @RequestBody LicenseDto licenseDto) {
        LicenseDto updatedLicense = licenseService.updateLicense(id, licenseDto);
        return ResponseEntity.created(URI.create("/api/licenses" + updatedLicense.id()))
                .body(updatedLicense);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLicense(@PathVariable Long id) {
        licenseService.deleteLicenseById(id);
        return ResponseEntity.noContent().build();
    }

}
