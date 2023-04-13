package com.bank.publicinfo.mapper;

import com.bank.publicinfo.dto.LicenseDto;
import com.bank.publicinfo.entity.BankDetails;
import com.bank.publicinfo.entity.License;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = {LicenseMapper.class})
public interface LicenseMapper {

    @Mapping(target = "bankDetailsId", source = "license.bankDetails.id")
    LicenseDto licenseEntityToDto (License license);

    @Mapping(target = "bankDetails", source = "bankDetailsId", qualifiedByName = "idToBankDetails")
    License licenseDtoToEntity (LicenseDto licenseDto);

    List<License> licenseListDtoToEntity (List<LicenseDto> licenseDtoList);

    List<LicenseDto> licenseListEntityToDto (List<License> licenseList);

    @Mapping(target = "id", ignore = true)
    void update(LicenseDto certificateDto, @MappingTarget License certificate);

    @Named("idToBankDetails")
    default BankDetails idToBankDetails(Long bankDetailsId) {
        if (bankDetailsId == null) {
            return null;
        }
        BankDetails bankDetails = new BankDetails();
        bankDetails.setId(bankDetailsId);
        return bankDetails;
    }
}
