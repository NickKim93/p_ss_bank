package com.bank.publicinfo.controller;

import com.bank.publicinfo.entity.License;
import com.bank.publicinfo.service.LicenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/licenses")
public class LicenseController {

    private final LicenseService licenseService;

    public LicenseController(LicenseService licenseService) {
        this.licenseService = licenseService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<License> getLicense(@PathVariable Long id) {
        Optional<License> license = licenseService.getLicenseById(id);
        return license.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<License>> getAllLicenses() {
        List<License> licenses = licenseService.getAllLicenses();
        if (licenses.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(licenses);
        }
    }

    @PostMapping
    public ResponseEntity<License> createLicense(@RequestBody License license) {
        License createdLicense = licenseService.createLicense(license);
        return ResponseEntity.created(URI.create("/api/licenses" + createdLicense.getId())).body(createdLicense);
    }

    @PutMapping("/{id}")
    public ResponseEntity<License> updatedLicense(@PathVariable Long id, @RequestBody License license) {
        Optional<License> updatedLicense = licenseService.updateLicense(id, license);
        return updatedLicense.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<License> deleteLicense(@PathVariable Long id) {
        boolean deleted = licenseService.deleteLicenseById(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

}
