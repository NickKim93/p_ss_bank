package com.bank.transfer.mapper;

import com.bank.transfer.dto.AccountTransferDto;
import com.bank.transfer.entity.AccountTransferEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccountTransferMapper {
    AccountTransferMapper ACCOUNT_TRANSFER_MAPPER = Mappers.getMapper(AccountTransferMapper.class);
    AccountTransferDto entityToDtoAccountTransfer(AccountTransferEntity accountTransferEntity);
    @Mapping(target = "id", ignore = true)
    AccountTransferEntity dtoToEntityAccountTransfer(AccountTransferDto accountTransferDto);

}
