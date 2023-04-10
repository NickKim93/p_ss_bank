package com.bank.publicinfo.service;

import com.bank.publicinfo.entity.BankDetails;
import com.bank.publicinfo.entity.Certificate;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface BankDetailsService {

    public Optional<BankDetails> getBankDetailsById(BigInteger id);

    public List<BankDetails> getAllBankDetails ();

    public BankDetails createBankDetails(BankDetails bankDetails);

    public Optional<BankDetails> updateBankDetails (BigInteger id, BankDetails bankDetails);

    public boolean deleteBankDetailsById(BigInteger id);
}
