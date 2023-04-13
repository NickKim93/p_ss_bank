package com.bank.transfer.service;

import com.bank.transfer.dto.CardTransferDto;
import com.bank.transfer.entity.CardTransferEntity;
import com.bank.transfer.mapper.CardTransferMapper;
import com.bank.transfer.repository.CardTransferRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
/**
 * Бизнес-логика приложения для Card Transfer Entity
 * @author Savenkov Artem
 */
@Service
public class CardTransferServiceImpl implements CardTransferService {

    private final CardTransferRepository cardTransferRepository;

    public CardTransferServiceImpl(CardTransferRepository cardTransferRepository) {
        this.cardTransferRepository = cardTransferRepository;
    }

    @Override
    public List<CardTransferEntity> getAll() {
        return cardTransferRepository.findAll();
    }

    @Override
    public CardTransferEntity getById(BigInteger id) {
        Optional<CardTransferEntity> optional = cardTransferRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new EntityNotFoundException("Card Transfer Entity с id: " + id + " не найден");
        }

    }

    @Override
    public CardTransferEntity saveOrUpdate(CardTransferDto cardTransferDto) {
        CardTransferEntity cardTransferEntity = CardTransferMapper.getCardTransferMapper
                .dtoToEntityCardTransfer(cardTransferDto);
        return cardTransferRepository.save(cardTransferEntity);
    }

    @Override
    public void delete(BigInteger id) {
        CardTransferEntity cardTransferEntity = cardTransferRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException(id.toString()));
        cardTransferRepository.delete(cardTransferEntity);
    }
}
