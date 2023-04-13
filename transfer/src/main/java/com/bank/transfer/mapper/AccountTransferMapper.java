package com.bank.transfer.mapper;

import com.bank.transfer.dto.AccountTransferDto;
import com.bank.transfer.entity.AccountTransferEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Итнерфэйс Account Transfer Mapper для получения Account Transfer Entity из DTO
 * @author Savenkov Artem
 */
@Mapper
public interface AccountTransferMapper {
    AccountTransferMapper getAccountTransferMapper = Mappers.getMapper(AccountTransferMapper.class);
    AccountTransferEntity dtoToEntityAccountTransfer(AccountTransferDto accountTransferDto);

}
