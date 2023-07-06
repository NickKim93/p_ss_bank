package com.bank.account.service;

import com.bank.account.entity.Audit;
import com.bank.account.repository.AuditRepository;
import com.bank.account.service.AuditServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
@ExtendWith(SpringExtension.class)
public class AuditServiceImpTest {
    @InjectMocks
    private AuditServiceImp auditService;
    @Mock
    private AuditRepository auditRepository;

    @Test
    public void shouldReturnAllAudit() {
        when(auditRepository.findAll()).thenReturn(getAudits());
        List<Audit> result = auditService.getAllAudits();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
    }

    @Test
    public void shouldReturnAuditById() {
        when(auditRepository.findById(1L)).thenReturn(Optional.of(getAudits().get(0)));
        Audit result = auditService.getAudit(1L);
        Assertions.assertEquals(1L, result.getId());
    }


    List<Audit> getAudits() {
        Audit firstAudit = new Audit(1L, "entity_type", "operation_type", "by_me", "by_me_too", Timestamp.valueOf(LocalDateTime.now()),
                Timestamp.valueOf(LocalDateTime.now()), "new_entity", "entity");
        Audit secondtAudit = new Audit(2L, "entity_type", "operation_type", "by_me", "by_me_too", Timestamp.valueOf(LocalDateTime.now()),
                Timestamp.valueOf(LocalDateTime.now()), "new_entity", "entity");
        return List.of(firstAudit, secondtAudit);
    }
}
