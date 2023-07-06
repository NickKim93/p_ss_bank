package com.bank.publicinfo.mapper;

import com.bank.publicinfo.dto.BankDetailsDto;
import com.bank.publicinfo.dto.CertificateDto;
import com.bank.publicinfo.dto.LicenseDto;
import com.bank.publicinfo.entity.BankDetails;
import com.bank.publicinfo.entity.Certificate;
import com.bank.publicinfo.entity.License;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", uses = {BranchMapper.class})
public interface BankDetailsMapper {

    BankDetailsDto bankDetailsEntityToDto (BankDetails bankDetails);

    BankDetails bankDetailsDtoToEntity (BankDetailsDto bankDetailsDto);

    Set<CertificateDto> certificateListToDto (Set<Certificate> certificates);

    Set<LicenseDto> licenseListToDto (Set<License> licenses);

    List<BankDetailsDto> bankDetailsListEntityToDto (List<BankDetails> bankDetails);

    @Mapping(target = "id", ignore = true)
    BankDetails updateEntityFromDto(BankDetailsDto dto, @MappingTarget BankDetails entity);

}
