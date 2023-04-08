package com.bank.antifraud.mapper;

import com.bank.antifraud.dto.SuspiciousCardTransferDto;
import com.bank.antifraud.entity.SuspiciousCardTransferEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SuspiciousCardTransferMapper {
    SuspiciousCardTransferMapper INSTANCE = Mappers.getMapper(SuspiciousCardTransferMapper.class);
    SuspiciousCardTransferEntity suspiciousCardTransferDtoToSuspiciousCardTransferEntity(SuspiciousCardTransferDto suspiciousCardTransferDto);
}
