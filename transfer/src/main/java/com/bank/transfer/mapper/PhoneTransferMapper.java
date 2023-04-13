package com.bank.transfer.mapper;

import com.bank.transfer.dto.PhoneTransferDto;
import com.bank.transfer.entity.PhoneTransferEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Итнерфэйс Phone Transfer Mapper для получения Phone Transfer Entity из DTO
 * @author Savenkov Artem
 */
@Mapper
public interface PhoneTransferMapper {
    PhoneTransferMapper getPhoneTransferMapper = Mappers.getMapper(PhoneTransferMapper.class);
    PhoneTransferEntity dtoToEntityPhoneTransfer(PhoneTransferDto phoneTransferDto);

}
