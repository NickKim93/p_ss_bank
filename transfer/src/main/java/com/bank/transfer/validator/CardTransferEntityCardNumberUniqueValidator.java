package com.bank.transfer.validator;

import com.bank.transfer.entity.CardTransferEntity;
import com.bank.transfer.service.CardTransferService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Slf4j
@Component
public class CardTransferEntityCardNumberUniqueValidator implements Validator {

    private final CardTransferService cardTransferService;

    public CardTransferEntityCardNumberUniqueValidator(CardTransferService cardTransferService) {
        this.cardTransferService = cardTransferService;
    }

    @Override
    public boolean supports(Class<?> classify) {
        return CardTransferEntity.class.equals(classify);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CardTransferEntity cardTransferEntity = (CardTransferEntity) target;
        log.info("try to validate cardTransferEntity: {}", cardTransferEntity);

        cardTransferService.getByCardNumber(cardTransferEntity.getCardNumber())
                .ifPresent((value) -> validateCardNumber(cardTransferEntity, value, errors)
                );
        log.info("success validate cardTransferEntity: {}", cardTransferEntity);
    }

    private void validateCardNumber(CardTransferEntity validated, CardTransferEntity fromDataBase, Errors errors) {
        if (!compareTransfer(validated, fromDataBase)) {
            errors.rejectValue(
                    "cardNumber",
                    String.format("cardNumber %s already exist!", fromDataBase.getCardNumber()),
                    String.format("cardNumber %s already exist!", fromDataBase.getCardNumber()));
        }
    }

    private boolean compareTransfer(CardTransferEntity validated, CardTransferEntity fromDataBase) {
        if (validated.getId() == null) {
            return false;
        }
        return validated.getId().equals(fromDataBase.getId());
    }

}


