package com.bank.publicinfo.mapper;

import com.bank.publicinfo.dto.AtmDto;
import com.bank.publicinfo.dto.BranchDto;
import com.bank.publicinfo.entity.Atm;
import com.bank.publicinfo.entity.Branch;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import java.util.Set;
import java.util.List;

@Mapper(componentModel = "spring")
public interface BranchMapper {

    BranchMapper INSTANCE = Mappers.getMapper(BranchMapper.class);
    BranchDto branchToDto(Branch branch);
    Branch branchToEntity(BranchDto branchDto);
    Set<AtmDto> toAtmDtoSet(Set<Atm> atmSet);

    List<BranchDto> branchListToDto (List<Branch> branchList);
    @Mapping(target = "id", ignore = true)
    Branch updateEntityFromDto(BranchDto dto, @MappingTarget Branch entity);
}
