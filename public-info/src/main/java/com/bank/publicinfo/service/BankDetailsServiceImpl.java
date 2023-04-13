package com.bank.publicinfo.service;

import com.bank.publicinfo.dto.BankDetailsDto;
import com.bank.publicinfo.entity.BankDetails;
import com.bank.publicinfo.mapper.BankDetailsMapper;
import com.bank.publicinfo.repository.BankDetailsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
public class BankDetailsServiceImpl implements BankDetailsService{

    private final BankDetailsRepository bankDetailsRepository;

    private final BankDetailsMapper bankDetailsMapper;

    public BankDetailsServiceImpl(BankDetailsRepository bankDetailsRepository, BankDetailsMapper bankDetailsMapper) {
        this.bankDetailsRepository = bankDetailsRepository;
        this.bankDetailsMapper = bankDetailsMapper;
    }

    @Override
    public BankDetailsDto getBankDetailsById(Long id) {
        BankDetails bankDetails = bankDetailsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("BankDetails not found"));
        return bankDetailsMapper.bankDetailsEntityToDto(bankDetails);
    }

    @Override
    public List<BankDetailsDto> getAllBankDetails() {
        List<BankDetails> bankDetails = bankDetailsRepository.findAll();
        return bankDetailsMapper.bankDetailsListEntityToDto(bankDetails);
    }

    @Override
    public BankDetailsDto createBankDetails(BankDetailsDto bankDetailsDto) {
        BankDetails bankDetails = bankDetailsMapper.bankDetailsDtoToEntity(bankDetailsDto);
        bankDetails = bankDetailsRepository.save(bankDetails);
        return bankDetailsMapper.bankDetailsEntityToDto(bankDetails);
    }

    @Override
    public BankDetailsDto updateBankDetails (Long id, BankDetailsDto bankDetailsDto) {
        BankDetails bankDetails = bankDetailsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("BankDetails not found"));
        bankDetailsMapper.updateEntityFromDto(bankDetailsDto, bankDetails);
        bankDetails = bankDetailsRepository.save(bankDetails);
            return bankDetailsMapper.bankDetailsEntityToDto(bankDetails);

    }

    @Override
    public void deleteBankDetailsById(Long id) {
        BankDetails bankDetails = bankDetailsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("BankDetails not found"));
        bankDetailsRepository.delete(bankDetails);
    }
}
