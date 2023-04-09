package com.bank.antifraud.mapper;

import com.bank.antifraud.dto.AuditDto;
import com.bank.antifraud.entity.AuditEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Маппер, который позволяет получить Entity из DTO для audit объекта
 * @author Makariy Petrov
 */
@Mapper
public interface AuditMapper {
    AuditMapper INSTANCE = Mappers.getMapper(AuditMapper.class);
    AuditEntity auditDtoToAuditEntity(AuditDto auditDto);
    AuditDto auditEntityToAuditDto(AuditEntity auditEntity);
}
