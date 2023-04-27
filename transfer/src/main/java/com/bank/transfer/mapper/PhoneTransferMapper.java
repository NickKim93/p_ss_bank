package com.bank.transfer.mapper;

import com.bank.transfer.dto.PhoneTransferDto;
import com.bank.transfer.entity.PhoneTransferEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PhoneTransferMapper {
    PhoneTransferMapper PHONE_TRANSFER_MAPPER = Mappers.getMapper(PhoneTransferMapper.class);
    PhoneTransferDto entityToDtoPhoneTransfer(PhoneTransferEntity phoneTransferEntity);
    @Mapping(target = "id", ignore = true)
    PhoneTransferEntity dtoToEntityPhoneTransfer(PhoneTransferDto phoneTransferDto);

}
