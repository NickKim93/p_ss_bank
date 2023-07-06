package com.bank.publicinfo.mapper;

import com.bank.publicinfo.dto.AtmDto;
import com.bank.publicinfo.entity.Atm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AtmMapper.class})
public interface AtmMapper {

    @Mapping(target = "branchId", source = "branch.id")
    AtmDto atmToDto(Atm atm);

    List<AtmDto> atmListToDtoList(List<Atm> atmList);

    List<Atm> atmListDtoToEntity(List<AtmDto> atmDtoList);


    Atm atmToEntity(AtmDto atmDto);

    @Mapping(target = "id", ignore = true)
    void update(AtmDto atmDto, @MappingTarget Atm atm);
}
