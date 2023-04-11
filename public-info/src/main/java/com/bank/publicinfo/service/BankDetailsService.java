package com.bank.publicinfo.service;

import com.bank.publicinfo.entity.BankDetails;
import com.bank.publicinfo.entity.Certificate;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface BankDetailsService {

    Optional<BankDetails> getBankDetailsById(Long id);

    List<BankDetails> getAllBankDetails ();

    BankDetails createBankDetails(BankDetails bankDetails);

    Optional<BankDetails> updateBankDetails (Long id, BankDetails bankDetails);

    boolean deleteBankDetailsById(Long id);
}
