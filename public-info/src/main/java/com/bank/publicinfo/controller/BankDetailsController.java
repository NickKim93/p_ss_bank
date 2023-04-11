package com.bank.publicinfo.controller;

import com.bank.publicinfo.entity.BankDetails;
import com.bank.publicinfo.service.BankDetailsService;
import com.bank.publicinfo.service.CertificateService;
import com.bank.publicinfo.service.LicenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bank_details")
public class BankDetailsController {

    private final BankDetailsService bankDetailsService;

    private final CertificateService certificateService;

    private final LicenseService licenseService;

    public BankDetailsController(BankDetailsService bankDetailsService, CertificateService certificateService, LicenseService licenseService) {
        this.bankDetailsService = bankDetailsService;
        this.certificateService = certificateService;
        this.licenseService = licenseService;
    }

    @GetMapping
    public ResponseEntity<List<BankDetails>> getAllBankDetails() {
        List<BankDetails> bankDetails = bankDetailsService.getAllBankDetails();
        if (bankDetails.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(bankDetails);
        }
    }

    @PostMapping
    public ResponseEntity<BankDetails> createLicense(@RequestBody BankDetails bankDetails) {
        BankDetails createdBankDetails = bankDetailsService.createBankDetails(bankDetails);
        return ResponseEntity.created(URI.create("/bank_details" + createdBankDetails.getId())).body(createdBankDetails);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BankDetails> updatedBankDetails(@PathVariable Long id, @RequestBody BankDetails bankDetails) {
        Optional<BankDetails> updatedBankDetails = bankDetailsService.updateBankDetails(id, bankDetails);
        return updatedBankDetails.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BankDetails> deleteBankDetails(@PathVariable Long id) {
        boolean deleted = bankDetailsService.deleteBankDetailsById(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

}

