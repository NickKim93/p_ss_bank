package com.bank.transfer.validator;

import com.bank.transfer.entity.AccountTransferEntity;
import com.bank.transfer.service.AccountTransferService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Slf4j
@Component
public class AccountTransferEntityAccountNumberUniqueValidator implements Validator {

    private final AccountTransferService accountTransferService;

    public AccountTransferEntityAccountNumberUniqueValidator(AccountTransferService accountTransferService) {
        this.accountTransferService = accountTransferService;
    }

    @Override
    public boolean supports(Class<?> classify) {
        return AccountTransferEntity.class.equals(classify);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AccountTransferEntity accountTransferEntity = (AccountTransferEntity) target;
        log.info("try to validate accountTransferEntity: {}", accountTransferEntity);
        accountTransferService.getByAccountNumber(accountTransferEntity.getAccountNumber())
                .ifPresent((value) -> validateAccountNumber(accountTransferEntity, value, errors)
                );
        log.info("success validate accountTransfer: {}", accountTransferEntity);
    }

    private void validateAccountNumber(AccountTransferEntity validated, AccountTransferEntity fromDataBase, Errors errors) {
        if (!compareTransfer(validated, fromDataBase)) {
            errors.rejectValue(
                    "accountNumber",
                    String.format("accountNumber %s already exist!", fromDataBase.getAccountNumber()),
                    String.format("accountNumber %s already exist!", fromDataBase.getAccountNumber()));
        }
    }

    private boolean compareTransfer(AccountTransferEntity validated, AccountTransferEntity fromDataBase) {
        if (validated.getId() == null) {
            return false;
        }
        return validated.getId().equals(fromDataBase.getId());
    }

}
