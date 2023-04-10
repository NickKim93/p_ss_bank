package com.bank.publicinfo.mapper;

import com.bank.publicinfo.dto.AtmDto;
import com.bank.publicinfo.entity.Atm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AtmMapper {

    AtmMapper INSTANCE = Mappers.getMapper(AtmMapper.class);

    AtmDto atmToDto(Atm atm);

    List<AtmDto> atmListToDtoList(List<Atm> atmList);
    Atm atmToEntity(AtmDto atmDto);
    @Mapping(target = "id", ignore = true)
    void update(AtmDto atmDto, @MappingTarget Atm atm);
}
