package com.bank.publicinfo.service;

import com.bank.publicinfo.dto.AuditDto;
import java.util.List;

public interface AuditService {

    List<AuditDto> getAudits ();

}
