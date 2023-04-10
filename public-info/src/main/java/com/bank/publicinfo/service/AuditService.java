package com.bank.publicinfo.service;

import com.bank.publicinfo.dto.AuditDto;
import java.math.BigInteger;
import java.util.List;

public interface AuditService {
    public AuditDto getAuditById(BigInteger id);

    public List<AuditDto> getAudits ();

    public AuditDto createAudit(AuditDto auditDto);

    public AuditDto updateAudit (BigInteger id, AuditDto auditDto);

    public void deleteAuditById(BigInteger id);
}
