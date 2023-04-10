package com.bank.antifraud.mapper;

import com.bank.antifraud.dto.SuspiciousPhoneTransfersDto;
import com.bank.antifraud.entity.SuspiciousPhoneTransfersEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SuspiciousPhoneTransfersMapper {
    SuspiciousPhoneTransfersMapper INSTANCE = Mappers.getMapper(SuspiciousPhoneTransfersMapper.class);
    SuspiciousPhoneTransfersEntity suspiciousPhoneTransfersDtoToSuspiciousPhoneTransfersEntity(SuspiciousPhoneTransfersDto suspiciousPhoneTransfersDto);
}
