package com.bank.account.service;

import com.bank.account.aspect.Auditable;
import com.bank.account.dto.AccountDetailsDTO;
import com.bank.account.entity.AccountDetails;
import com.bank.account.exception.AccountDetailsNotFoundException;
import com.bank.account.mapper.AccountDetailsMapper;
import com.bank.account.repository.AccountRepository;
import com.bank.account.util.AuditingActionType;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountDetailsServiceImp implements AccountDetailsService {
    private final Logger logger = LoggerFactory.getLogger(AccountDetailsServiceImp.class);
    private final AccountRepository accountRepository;

    @Transactional
    @Auditable(actionType = AuditingActionType.CREATE)
    @Override
    public void save(AccountDetailsDTO detailsDTO) {
        logger.info("запущен метод save");
        accountRepository.save(AccountDetailsMapper.INSTANCE.toEntity(detailsDTO));
    }

    @Override
    public List<AccountDetailsDTO> getAllAccountDetails() {
        logger.info("запущен метод getAllAccountDetails");
        return accountRepository.findAll().stream().map(AccountDetailsMapper.INSTANCE::toDTO).collect(Collectors.toList());
    }

    @Override
    public AccountDetailsDTO getAccountDetails(Long id) {
        logger.info("запущен метод getAccountDetails");
        return AccountDetailsMapper.INSTANCE.toDTO(accountRepository.findById(id).orElseThrow(AccountDetailsNotFoundException::new));
    }

    @Override
    @Transactional
    @Auditable(actionType = AuditingActionType.DELETE)
    public void deleteById(Long id) {
        logger.info("запущен метод deleteById");
        accountRepository.deleteById(id);
    }

    @Override
    @Transactional
    @Auditable(actionType = AuditingActionType.UPDATE)
    public void updateAccountDetails(AccountDetailsDTO detailsDTO) {
        logger.info("запущен метод updateAccountDetails");
        AccountDetails updateDetails = accountRepository
                .findAccountDetailsByAccountNumber(detailsDTO.getAccountNumber())
                .orElseThrow(AccountDetailsNotFoundException::new);
        updateDetails.setNegativeBalance(detailsDTO.getNegativeBalance());
        accountRepository.save(updateDetails);
    }
}
