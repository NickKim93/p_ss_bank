package com.bank.account.service;

import com.bank.account.aspect.Auditable;
import com.bank.account.dto.AccountDetailsDTO;
import com.bank.account.util.AuditingActionType;

import java.util.List;

public interface AccountDetailsService {
    @Auditable(actionType = AuditingActionType.CREATE)
    void save(AccountDetailsDTO detailsDTO);

    List<AccountDetailsDTO> getAllAccountDetails();

    AccountDetailsDTO getAccountDetails(Long id);

    void deleteById(Long id);

    @Auditable(actionType = AuditingActionType.UPDATE)
    void updateAccountDetails(AccountDetailsDTO detailsDTO);
}
