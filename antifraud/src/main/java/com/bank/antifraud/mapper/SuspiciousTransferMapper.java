package com.bank.antifraud.mapper;

import com.bank.antifraud.dto.SuspiciousTransferDto;
import com.bank.antifraud.entity.AccountEntity;
import com.bank.antifraud.entity.CardEntity;
import com.bank.antifraud.entity.PhoneEntity;
import com.bank.antifraud.entity.SuspiciousTransfer;
import com.bank.antifraud.exception.IncorrectTransferTypeException;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Маппер для подозрительных переводов
 *
 * @author Makariy Petrov
 */
@Mapper
public interface SuspiciousTransferMapper {
    SuspiciousTransferMapper INSTANCE = Mappers.getMapper(SuspiciousTransferMapper.class);

    AccountEntity suspiciousTransferDtoToSuspiciousAccountTransfersEntity(SuspiciousTransferDto accountDto);
    PhoneEntity suspiciousTransferDtoToSuspiciousPhoneTransfersEntity(SuspiciousTransferDto phoneDto);
    CardEntity suspiciousTransferDtoToSuspiciousCardTransferEntity(SuspiciousTransferDto cardDto);

    default SuspiciousTransfer suspiciousTransferDtoToSuspiciousTransfer(SuspiciousTransferDto suspiciousTransferDto) {
        return switch (suspiciousTransferDto.getType()) {
            case ("card") -> suspiciousTransferDtoToSuspiciousCardTransferEntity(suspiciousTransferDto);
            case ("phone") -> suspiciousTransferDtoToSuspiciousPhoneTransfersEntity(suspiciousTransferDto);
            case ("account") -> suspiciousTransferDtoToSuspiciousAccountTransfersEntity(suspiciousTransferDto);
            default -> throw new IncorrectTransferTypeException();
        };
    }
}