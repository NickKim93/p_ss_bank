package com.bank.publicinfo.mapper;

import com.bank.publicinfo.dto.AuditDto;
import com.bank.publicinfo.entity.Audit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AuditMapper.class})
public interface AuditMapper {

    @Mapping(target = "id", ignore = true)
    Audit AuditDtoToEntity(AuditDto auditDto);

    AuditDto AuditEntityToDto(Audit audit);

    List<AuditDto> auditListToDto (List<Audit> auditList);
}
