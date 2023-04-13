package com.bank.transfer.service;

import com.bank.transfer.dto.AccountTransferDto;
import com.bank.transfer.entity.AccountTransferEntity;
import com.bank.transfer.mapper.AccountTransferMapper;
import com.bank.transfer.repository.AccountTransferRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
/**
 * Бизнес-логика приложения для Account Transfer Entity
 * @author Savenkov Artem
 */
@Service
public class AccountTransferServiceImpl implements AccountTransferService {

    private final AccountTransferRepository accountTransferRepository;

    public AccountTransferServiceImpl(AccountTransferRepository accountTransferRepository) {
        this.accountTransferRepository = accountTransferRepository;
    }
    @Override
    public List<AccountTransferEntity> getAll() {
        return accountTransferRepository.findAll();
    }

    @Override
    public AccountTransferEntity getById(BigInteger id) {
        Optional<AccountTransferEntity> optional = accountTransferRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new EntityNotFoundException("Account Transfer Entity с id: " + id + " не найден");
        }
    }

    @Override
    public AccountTransferEntity saveOrUpdate(AccountTransferDto accountTransferDto) {
        AccountTransferEntity accountTransferEntity = AccountTransferMapper.getAccountTransferMapper
                .dtoToEntityAccountTransfer(accountTransferDto);
        return accountTransferRepository.save(accountTransferEntity);
    }

    @Override
    public void delete(BigInteger id) {
        AccountTransferEntity accountTransferEntity = accountTransferRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException(id.toString()));
        accountTransferRepository.delete(accountTransferEntity);
    }
}
