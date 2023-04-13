package com.bank.publicinfo.service;

import com.bank.publicinfo.dto.BankDetailsDto;
import java.util.List;


public interface BankDetailsService {

    BankDetailsDto getBankDetailsById(Long id);

    List<BankDetailsDto> getAllBankDetails ();

    BankDetailsDto createBankDetails(BankDetailsDto bankDetailsDto);

    BankDetailsDto updateBankDetails (Long id, BankDetailsDto bankDetailsDto);

    void deleteBankDetailsById(Long id);
}
