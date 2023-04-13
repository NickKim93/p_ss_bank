package com.bank.publicinfo.mapper;

import com.bank.publicinfo.dto.CertificateDto;
import com.bank.publicinfo.entity.BankDetails;
import com.bank.publicinfo.entity.Certificate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CertificateMapper.class})
public interface CertificateMapper {

    @Mapping(target = "bankDetailsId", source = "certificate.bankDetails.id")
    CertificateDto certificateEntityToDto (Certificate certificate);
    @Mapping(target = "bankDetails", source = "bankDetailsId", qualifiedByName = "idToBankDetails")
    Certificate certificateDtoToEntity (CertificateDto certificateDto);

    List<Certificate> certificateListDtoToEntity (List<CertificateDto> certificateDtoList);

    List<CertificateDto> certificateListEntityToDto (List<Certificate> certificateList);

    @Mapping(target = "id", ignore = true)
    void update(CertificateDto certificateDto, @MappingTarget Certificate certificate);

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
