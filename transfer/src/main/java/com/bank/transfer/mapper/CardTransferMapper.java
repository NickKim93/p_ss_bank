package com.bank.transfer.mapper;

import com.bank.transfer.dto.CardTransferDto;
import com.bank.transfer.entity.CardTransferEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CardTransferMapper {
    CardTransferMapper CARD_TRANSFER_MAPPER = Mappers.getMapper(CardTransferMapper.class);
    CardTransferDto entityToDtoCardTransfer(CardTransferEntity cardTransferEntity);
    @Mapping(target = "id", ignore = true)
    CardTransferEntity dtoToEntityCardTransfer(CardTransferDto cardTransferDto);
}
