package com.bank.publicinfo.service;

import com.bank.publicinfo.auditlistener.Auditable;
import com.bank.publicinfo.dto.BankDetailsDto;
import com.bank.publicinfo.entity.BankDetails;
import com.bank.publicinfo.mapper.BankDetailsMapper;
import com.bank.publicinfo.repository.BankDetailsRepository;
import io.micrometer.core.annotation.Timed;
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
    @Timed(value = "bank_details_service.findAll")
    public List<BankDetailsDto> getAllBankDetails() {
        List<BankDetails> bankDetails = bankDetailsRepository.findAll();
        return bankDetailsMapper.bankDetailsListEntityToDto(bankDetails);
    }

    @Override
    @Auditable(operationType = "create")
    @Timed(value = "bank_details_service.create")
    public BankDetailsDto createBankDetails(BankDetailsDto bankDetailsDto) {
        BankDetails bankDetails = bankDetailsMapper.bankDetailsDtoToEntity(bankDetailsDto);
        bankDetails = bankDetailsRepository.save(bankDetails);
        return bankDetailsMapper.bankDetailsEntityToDto(bankDetails);
    }

    @Override
    @Auditable(operationType = "update")
    @Timed(value = "bank_details_service.update")
    public BankDetailsDto updateBankDetails (Long id, BankDetailsDto bankDetailsDto) {
        BankDetails bankDetails = bankDetailsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("BankDetails not found"));
        bankDetailsMapper.updateEntityFromDto(bankDetailsDto, bankDetails);
        bankDetails = bankDetailsRepository.save(bankDetails);
            return bankDetailsMapper.bankDetailsEntityToDto(bankDetails);

    }

    @Override
    @Auditable(operationType = "delete")
    @Timed(value = "bank_details_service.delete")
    public void deleteBankDetailsById(Long id) {
        BankDetails bankDetails = bankDetailsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("BankDetails not found"));
        bankDetailsRepository.delete(bankDetails);
    }
}
