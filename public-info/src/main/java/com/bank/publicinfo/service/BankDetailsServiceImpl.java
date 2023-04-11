package com.bank.publicinfo.service;

import com.bank.publicinfo.entity.BankDetails;
import com.bank.publicinfo.repository.BankDetailsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BankDetailsServiceImpl implements BankDetailsService{

    private final BankDetailsRepository bankDetailsRepository;

    public BankDetailsServiceImpl(BankDetailsRepository bankDetailsRepository) {
        this.bankDetailsRepository = bankDetailsRepository;
    }

    @Override
    public Optional<BankDetails> getBankDetailsById(Long id) {
        return bankDetailsRepository.findById(id);
    }

    @Override
    public List<BankDetails> getAllBankDetails() {
        return bankDetailsRepository.findAll();
    }

    @Override
    public BankDetails createBankDetails(BankDetails bankDetails) {
        return bankDetailsRepository.save(bankDetails);
    }

    @Override
    public Optional<BankDetails> updateBankDetails (Long id, BankDetails bankDetails) {
        Optional<BankDetails> existingBankDetails = bankDetailsRepository.findById(id);
        if (existingBankDetails.isPresent()) {
            BankDetails updatedBankDetails = existingBankDetails.get();
            updatedBankDetails.setBik(bankDetails.getBik());
            updatedBankDetails.setKpp(bankDetails.getKpp());
            updatedBankDetails.setCorAccount(bankDetails.getCorAccount());
            updatedBankDetails.setCity(bankDetails.getCity());
            updatedBankDetails.setJointStockCompany(bankDetails.getJointStockCompany());
            updatedBankDetails.setName(bankDetails.getName());
            updatedBankDetails.setCertificates(bankDetails.getCertificates());
            updatedBankDetails.setLicenses(bankDetails.getLicenses());
            return Optional.of(bankDetailsRepository.save(updatedBankDetails));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean deleteBankDetailsById(Long id) {
        Optional<BankDetails> bankDetails = bankDetailsRepository.findById(id);
        if (bankDetails.isPresent()) {
            bankDetailsRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
