package com.bank.publicinfo.service;

import com.bank.publicinfo.dto.AuditDto;
import java.math.BigInteger;
import java.util.List;

public interface AuditService {
    AuditDto getAuditById(Long id);

    List<AuditDto> getAudits ();

    AuditDto createAudit(AuditDto auditDto);

    AuditDto updateAudit (Long id, AuditDto auditDto);

    void deleteAuditById(Long id);
}
