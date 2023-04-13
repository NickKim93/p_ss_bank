package com.bank.transfer.mapper;

import com.bank.transfer.dto.AuditDto;
import com.bank.transfer.entity.AuditEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Итнерфэйс Audit Mapper для получения Audit Entity из DTO
 * @author Savenkov Artem
 */
@Mapper
public interface AuditMapper {
    AuditMapper getAuditMapper = Mappers.getMapper(AuditMapper.class);
    AuditEntity dtoToEntityAudit(AuditDto auditDto);

}
