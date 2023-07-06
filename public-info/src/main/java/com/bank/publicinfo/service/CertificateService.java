package com.bank.publicinfo.service;

import com.bank.publicinfo.dto.CertificateDto;
import java.util.List;

public interface CertificateService {

   CertificateDto getCertificateById(Long id);

    List<CertificateDto> getAllCertificates ();

    CertificateDto createCertificate(CertificateDto certificateDto);

    CertificateDto updateCertificate(Long id, CertificateDto certificateDto);

    void deleteCertificateById(Long id);
}
