package com.bank.transfer.mapper;

import com.bank.transfer.dto.CardTransferDto;
import com.bank.transfer.entity.CardTransferEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Итнерфэйс Card Transfer Mapper для получения Card Transfer Entity из DTO
 * @author Savenkov Artem
 */
@Mapper
public interface CardTransferMapper {
    CardTransferMapper getCardTransferMapper = Mappers.getMapper(CardTransferMapper.class);
    CardTransferEntity dtoToEntityCardTransfer(CardTransferDto cardTransferDto);
}
