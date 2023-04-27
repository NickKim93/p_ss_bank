package com.bank.transfer.mapper;

import com.bank.transfer.dto.AuditDto;
import com.bank.transfer.entity.AuditEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AuditMapper {
    AuditMapper AUDIT_MAPPER = Mappers.getMapper(AuditMapper.class);
    AuditDto entityToDtoAudit(AuditEntity auditEntity);

}
