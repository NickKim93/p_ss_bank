package com.bank.publicinfo.controller;

import com.bank.publicinfo.dto.BankDetailsDto;
import com.bank.publicinfo.service.BankDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank_details")
public class BankDetailsController {

    private final BankDetailsService bankDetailsService;


    public BankDetailsController(BankDetailsService bankDetailsService) {
        this.bankDetailsService = bankDetailsService;
    }

    @GetMapping
    public ResponseEntity<List<BankDetailsDto>> getAllBankDetails() {
        List<BankDetailsDto> bankDetails = bankDetailsService.getAllBankDetails();
            return ResponseEntity.ok(bankDetails);
    }

    @PostMapping
    public ResponseEntity<BankDetailsDto> createLicense(@RequestBody BankDetailsDto bankDetailsDto) {
        BankDetailsDto createdBankDetails = bankDetailsService.createBankDetails(bankDetailsDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBankDetails);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BankDetailsDto> updatedBankDetails(@PathVariable Long id, @RequestBody BankDetailsDto bankDetailsDto) {
        BankDetailsDto updatedBankDetails = bankDetailsService.updateBankDetails(id, bankDetailsDto);
        return ResponseEntity.ok(updatedBankDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBankDetails(@PathVariable Long id) {
       bankDetailsService.deleteBankDetailsById(id);
        return ResponseEntity.noContent().build();
    }
}

