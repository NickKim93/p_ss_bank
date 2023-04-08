package com.bank.antifraud.mapper;

import com.bank.antifraud.dto.AuditDto;
import com.bank.antifraud.dto.SuspiciousAccountTransfersDto;
import com.bank.antifraud.entity.AuditEntity;
import com.bank.antifraud.entity.SuspiciousAccountTransfersEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SuspiciousAccountTransfersMapper {
    SuspiciousAccountTransfersMapper INSTANCE = Mappers.getMapper(SuspiciousAccountTransfersMapper.class);
    SuspiciousAccountTransfersEntity suspiciousAccountTransfersDtoToSuspiciousAccountTransfersEntity(SuspiciousAccountTransfersDto suspiciousAccountTransfersDto);
}
