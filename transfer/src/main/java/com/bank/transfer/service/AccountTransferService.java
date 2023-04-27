package com.bank.transfer.service;
import com.bank.transfer.entity.AccountTransferEntity;
import java.util.List;
import java.util.Optional;

public interface AccountTransferService {
    List<AccountTransferEntity> getAll();
    Optional<AccountTransferEntity> getById(Long id);
    Optional<AccountTransferEntity> getByAccountNumber(Long accountNumber);
    void save(AccountTransferEntity accountTransferEntity);
    void update(Long id, AccountTransferEntity accountTransferEntity);
    void delete(Long id);

}
